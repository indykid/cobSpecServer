package kg.jarkyn.server.incoming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class RequestParser {

    public Request parse(InputStream inputStream) {
        String requestLine = getRequestLine(inputToReader(inputStream));
        return new Request(extractVerb(requestLine), extractPath(requestLine));
    }

    private String extractVerb(String requestLine) {
        return requestLine.split(" ")[0];
    }

    private String extractPath(String requestLine) {
        return requestLine.split(" ")[1];
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
}
