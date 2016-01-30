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
        String content = formatStatus() + formatHeaders() + body;
        return content.getBytes();
    }

    private String formatHeaders() {
        return headers + "\r\n\r\n";
    }

    private String formatStatus() {
        if (headers.isEmpty()) {
            return statusLine;
        }
        return statusLine + "\r\n" ;
    }
}
