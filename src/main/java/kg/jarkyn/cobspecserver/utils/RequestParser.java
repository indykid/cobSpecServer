package kg.jarkyn.cobspecserver.utils;

import kg.jarkyn.cobspecserver.data.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class RequestParser {
    private RequestLineParser requestLineParser = new RequestLineParser();

    public Request parse(InputStream inputStream) {
        BufferedReader reader = inputToReader(inputStream);
        String requestLine = extractRequestLine(reader);
        return new Request(extractMethod(requestLine),
                           extractPath(requestLine));
    }

    private String extractMethod(String requestLine) {
        return requestLineParser.extractMethod(requestLine);
    }

    private String extractPath(String requestLine) {
        return requestLineParser.extractPath(requestLine);
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
            String path = pathWithParams(requestLine);
            if (path.contains("?")) {
                path = path.split("\\?", 2)[0];
            }
            return path;
        }

        private String pathWithParams(String requestLine) {
            return requestLine.split(" ")[1];
        }
    }
}
