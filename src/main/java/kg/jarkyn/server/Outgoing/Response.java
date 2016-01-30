package kg.jarkyn.server.outgoing;

public class Response {
    private String statusLine;
    private String headers;
    private final String body;

    public Response(String statusLine, String headers, String body) {
        this.statusLine = statusLine;
        this.headers = headers;
        this.body = body;
    }

    public byte[] getContent() {
        String content = statusLine + "\r\n" + headers + "\r\n\r\n" + body;
        return content.getBytes();
    }
}
