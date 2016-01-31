package kg.jarkyn.server.incoming;

import java.util.HashMap;

public class Request {
    private final String method;
    private final String path;
    private HashMap<String, String> headers;
    private HashMap<String, String> params;
    private String body;

    public Request(String method, String path) {
        this.method = method;
        this.path = path;
    }
    public Request(String method,
                   String path,
                   HashMap<String, String> headers,
                   String body,
                   HashMap<String, String> params) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.body = body;
        this.params = params;
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

    public HashMap<String, String> getParams() {
        return params;
    }
}
