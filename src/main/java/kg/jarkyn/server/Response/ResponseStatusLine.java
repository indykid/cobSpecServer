package kg.jarkyn.server.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseStatusLine {
    private static final String PROTOCOL = "HTTP/1.1";
    private final String statusCode;
    private final static Map<String, String> STATUS_DESCRIPTIONS = new HashMap<String, String>() {
        {
            put("200", "OK");
        }
    };

    public ResponseStatusLine(String statusCode) {
        this.statusCode = statusCode;
    }

    public String format() {
        return String.join(" ", getStatusFields());
    }

    private List<String> getStatusFields() {
        return Arrays.asList(PROTOCOL, statusCode, STATUS_DESCRIPTIONS.get(statusCode));
    }
}
