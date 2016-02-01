package kg.jarkyn.server.incoming;

import java.util.HashMap;

public class Request {
    private final String method;
    private final String path;
    private HashMap<String, String> headers;
    private String params;
    private String body;

    public Request(String method, String path, String params) {
        this.method = method;
        this.path = path;
        this.params = params;
        this.headers = new HashMap<>();
        this.body = "";
    }
    public Request(String method,
                   String path,
                   HashMap<String, String> headers,
                   String body,
                   String params) {
        this.method = method;
        this.path = path;
        this.params = params;
        this.headers = headers;
        this.body = body;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public String getParams() {
        return params;
    }
}
