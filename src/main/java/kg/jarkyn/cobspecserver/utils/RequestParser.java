package kg.jarkyn.cobspecserver.utils;

import kg.jarkyn.cobspecserver.data.Request;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestParser {
    private static final String CONTENT_LENGTH_KEY = "Content-Length";
    private RequestLineParser requestLineParser = new RequestLineParser();

    public Request parse(InputStream inputStream) {
        BufferedReader reader = StreamHandler.setupReader(inputStream);
        String requestLine = extractRequestLine(reader);
        Map<String, String> headers = extractHeaders(reader);
        return new Request(extractMethod(requestLine),
                           extractPath(requestLine),
                           headers,
                           extractParams(requestLine),
                           extractBody(reader, headers));
    }

    private String extractRequestLine(BufferedReader reader) {
        return StreamHandler.readLine(reader);
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

    private String extractBody(BufferedReader reader, Map<String, String> headers) {
        if (isBodyPresent(headers)) {
            return new String(StreamHandler.readCharacters(reader, getContentLength(headers)));
        }
        return "";
    }

    private Map<String, String> extractHeaders(BufferedReader reader) {
        Map<String, String> headers = new HashMap<>();
        List<String> headerLines = StreamHandler.readUntilEmptyLine(reader);
        headerLines.stream()
                .filter(line -> line.contains(":"))
                .forEach(line -> {
                    String[] pair = line.split(":", 2);
                    headers.put(pair[0].trim(), pair[1].trim());
                });
        return headers;
    }

    private boolean isBodyPresent(Map<String, String> headers) {
        return headers.containsKey(CONTENT_LENGTH_KEY);
    }

    private int getContentLength(Map<String, String> headers) {
        return Integer.parseInt(headers.get(CONTENT_LENGTH_KEY));
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