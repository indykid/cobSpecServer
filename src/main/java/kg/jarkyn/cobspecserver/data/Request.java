package kg.jarkyn.cobspecserver.data;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String method;
    private String path;
    private Map<String, String> headers;

    public Request(String method, String path) {
        this(method, path, new HashMap<>());
    }

    public Request(String method, String path, Map<String, String> headers) {
        this.method = method;
        this.path = path;
        this.headers = headers;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHeader(String headerName) {
        return headers.get(headerName);
    }
}
