package kg.jarkyn.cobspecserver.utils;

import kg.jarkyn.cobspecserver.data.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestParser {
    private RequestLineParser requestLineParser = new RequestLineParser();

    public Request parse(InputStream inputStream) {
        BufferedReader reader = inputToReader(inputStream);
        String requestLine = extractRequestLine(reader);
        return new Request(extractMethod(requestLine),
                           extractPath(requestLine),
                           extractHeaders(reader),
                           extractParams(requestLine));
    }

    private Map<String, String> extractHeaders(BufferedReader reader) {
        return new HashMap<>();
    }

    private String extractMethod(String requestLine) {
        return requestLineParser.extractMethod(requestLine);
    }

    private String extractPath(String requestLine) {
        return requestLineParser.extractPath(requestLine);
    }

    private Map<String, String> extractParams(String requestLine) {
        return requestLineParser.extractParams(requestLine);
    }

    private String extractRequestLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedReader inputToReader(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        return new BufferedReader(inputStreamReader);
    }

    private class RequestLineParser {

        public String extractMethod(String requestLine) {
            return requestLine.split(" ")[0];
        }

        public String extractPath(String requestLine) {
            String path = pathWithQuery(requestLine);
            if (path.contains("?")) {
                path = path.split("\\?", 2)[0];
            }
            return path;
        }

        public Map<String, String> extractParams(String requestLine) {
            return queryToParams(extractQuery(requestLine));
        }

        private Map<String, String> queryToParams(String queryString) {
            Map<String, String> params = new HashMap<>();
            List<String> queries = Arrays.asList(queryString.split("&"));

            for (String query : queries) {
                String[] pair = query.split("=", 2);
                params.put(pair[0], pair[1]);
            }
            return params;
        }

        private String extractQuery(String requestLine) {
            String queryString = "";
            String path = pathWithQuery(requestLine);
            if (path.contains("?")) {
                queryString = path.split("\\?", 2)[1];
            }
            return queryString;
        }

        private String pathWithQuery(String requestLine) {
            return requestLine.split(" ")[1];
        }
    }
}