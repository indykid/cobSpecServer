package kg.jarkyn.server.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class RequestParser {

    public static ParsedRequest parse(InputStream inputStream) {
        String requestLine = getRequestLine(inputToReader(inputStream));
        return new ParsedRequest(extractVerb(requestLine), extractPath(requestLine));
    }

    private static String extractVerb(String requestLine) {
        return requestLine.split(" ")[0];
    }

    private static String extractPath(String requestLine) {
        return requestLine.split(" ")[1];
    }

    private static BufferedReader inputToReader(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        return new BufferedReader(inputStreamReader);
    }

    private static String getRequestLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
