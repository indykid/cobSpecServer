package kg.jarkyn.server.incoming;

public class ParsedRequest {
    private final String verb;
    private final String path;

    public ParsedRequest(String verb, String path) {
        this.verb = verb;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getVerb() {
        return verb;
    }
}
