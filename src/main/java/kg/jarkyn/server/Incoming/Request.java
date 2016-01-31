package kg.jarkyn.server.incoming;

public class Request {
    private final String method;
    private final String path;

    public Request(String method, String path) {
        this.method = method;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }
}
