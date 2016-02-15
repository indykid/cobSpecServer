package kg.jarkyn.cobspecserver.data;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String method;
    private String path;
    private Map<String, String> headers;
    private Map<String, String> params;

    public Request(String method, String path) {
        this(method, path, new HashMap<>());
    }

    public Request(String method, String path, Map<String, String> headers) {
        this(method, path, headers, new HashMap<>());
    }

    public Request(String method, String path, Map<String, String> headers, Map<String, String> params) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.params = params;
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

    public Map<String, String> getParams() {
        return params;
    }
}
