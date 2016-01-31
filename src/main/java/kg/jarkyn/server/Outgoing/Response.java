package kg.jarkyn.server.outgoing;

public class Response {
    private String statusLine;
    private String headers;
    private byte[] body;

    public Response(String statusLine, String headers, byte[] body) {
        this.statusLine = statusLine;
        this.headers = headers;
        this.body = body;
    }

    public byte[] getByteContent() {
        String header = formatStatus() + formatHeaders();
        return combine(header.getBytes(), body);
    }

    private byte[] combine(byte[] header, byte[] body) {
        byte[] combined = new byte[header.length + body.length];
        System.arraycopy(header, 0,combined, 0, header.length);
        System.arraycopy(body, 0, combined, header.length, body.length);
        return combined;
    }

    private String formatHeaders() {
        return headers + "\r\n\r\n";
    }

    private String formatStatus() {
        return statusLine + "\r\n" ;
    }

    public String getContent() {
        return formatStatus() + formatHeaders() + new String(body);
    }
}
