package kg.jarkyn.cobspecserver.data;

public class Response {
    private String status;
    private String headers;
    private byte[] body;

    public Response(String status, String headers, byte[] body) {
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    public Response(String status) {
        this(status, "", "".getBytes());
    }

    public Response(String status, String headers) {
        this(status, headers, "".getBytes());
    }

    public byte[] getByteContent() {
        String header = formatStatus() + formatHeaders();
        return combine(header.getBytes(), body);
    }

    public String getContent() {
        return formatStatus() + formatHeaders() + new String(body);
    }

    private String formatStatus() {
        return status + "\r\n" ;
    }

    private String formatHeaders() {
        return headers + "\r\n\r\n";
    }

    private byte[] combine(byte[] header, byte[] body) {
        byte[] combined = new byte[header.length + body.length];
        System.arraycopy(header, 0,combined, 0, header.length);
        System.arraycopy(body, 0, combined, header.length, body.length);
        return combined;
    }
}
