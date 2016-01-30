package kg.jarkyn.server.outgoing;

public class Response {
    private final String body;

    public Response(String body) {
        this.body = body;
    }

    public byte[] getBytes() {
        return body.getBytes();
    }
}
