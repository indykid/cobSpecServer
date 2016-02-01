package kg.jarkyn.server.incoming;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestParser {

    public Request parseRequestLine(InputStream inputStream) {
        BufferedReader reader = inputToReader(inputStream);
        String requestLine = getRequestLine(reader);
        return new Request(extractMethod(requestLine),
                           extractPath(requestLine),
                           extractParams(requestLine));
    }

    public Request parse(InputStream inputStream) {
        BufferedReader reader = inputToReader(inputStream);
        String requestLine = getRequestLine(reader);
        return new Request(extractMethod(requestLine),
                           extractPath(requestLine),
                           extractHeaders(reader),
                           extractBody(reader),
                           extractParams(requestLine));
    }

    private String extractMethod(String requestLine) {
        return requestLine.split(" ")[0];
    }

    private String extractPath(String requestLine) {
        int pathPosition = 0;
        String withParams = extractPathWithParams(requestLine);

        if (withParams.contains("?")) {
            return withParams.split("\\?")[pathPosition];
        } else {
            return withParams;
        }
    }

    private String extractParams(String requestLine) {
        int fieldPosition = 0;
        int valuePosition = 1;

        StringBuilder builder = new StringBuilder();

        if (requestLine.contains("&")) {
            String[] splitParams = extractRawParams(requestLine).split("&");
            for (String param : splitParams) {
                String key = param.split("=", 2)[fieldPosition];
                String value = decode(param.split("=", 2)[valuePosition]);
                builder.append(key + " = " + value + "\n");
            }
        }

        return builder.toString();
    }

    private String extractPathWithParams(String requestLine) {
        return requestLine.split(" ")[1];
    }

    private String decode(String param) {
        try {
            return URLDecoder.decode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String extractRawParams(String requestLine) {
        int paramsPosition = 1;
        String rawParams = "";
        if (requestLine.contains("?")) {
            rawParams = extractPathWithParams(requestLine).split("\\?")[paramsPosition];
        }
        return rawParams;
    }

    private BufferedReader inputToReader(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        return new BufferedReader(inputStreamReader);
    }

    private String getRequestLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HashMap<String, String> extractHeaders(BufferedReader reader) {
        int fieldPosition = 0;
        int valuePosition = 1;
        HashMap<String, String> headers = new HashMap<>();

        readHeaderLines(reader)
                .stream()
                .filter(line -> line.contains(":"))
                .forEach(line -> {
                    String[] parts = line.split(":", 2);
                    headers.put(parts[fieldPosition].trim(), parts[valuePosition].trim());
                });

        return headers;
    }

    private String extractBody(BufferedReader reader) {
        try {
            String body = reader.readLine();
            reader.close();
            if (body != null) {
                return body;
            } else {
                return "";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> readHeaderLines(BufferedReader reader) {
        List<String> lines = new ArrayList<>();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    break;
                }
                lines.add(line);
            }
            return lines;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
