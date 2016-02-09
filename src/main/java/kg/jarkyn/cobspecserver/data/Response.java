package kg.jarkyn.cobspecserver.data;

import kg.jarkyn.cobspecserver.utils.Status;

public class Response {
    private static final String STATUS_TEMPLATE = "HTTP/1.1 %s %s";
    private Status status;
    private String headers;
    private byte[] body;

    public Response(Status status, String headers, byte[] body) {
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    public Response(Status status) {
        this(status, "", "".getBytes());
    }

    public Response(Status status, String headers) {
        this(status, headers, "".getBytes());
    }

    public Status getStatus() {
        return status;
    }

    public String getHeaders() {
        return headers;
    }

    public byte[] getBody() {
        return body;
    }

    public byte[] getByteContent() {
        String header = formatStatus() + formatHeaders();
        return combine(header.getBytes(), body);
    }

    private String formatStatus() {
        return String.format(STATUS_TEMPLATE, status.getCode(), status.getDescription()) + "\r\n";
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
