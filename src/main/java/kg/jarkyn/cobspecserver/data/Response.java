package kg.jarkyn.cobspecserver.data;

import kg.jarkyn.cobspecserver.utils.Status;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private static final String STATUS_TEMPLATE = "HTTP/1.1 %s %s";
    private Status status;
    private Map<String, String> headers;
    private byte[] body;

    public Response(Status status, Map<String, String> headers, byte[] body) {
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    public Response(Status status) {
        this(status, new HashMap<>(), "".getBytes());
    }

    public Response(Status status, Map<String, String> headers) {
        this(status, headers, "".getBytes());
    }

    public Status getStatus() {
        return status;
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
        String formatted = "";
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            formatted += entry.getKey() + ": " + entry.getValue();
        }
        return formatted + "\r\n\r\n";
    }

    private byte[] combine(byte[] header, byte[] body) {
        byte[] combined = new byte[header.length + body.length];
        System.arraycopy(header, 0,combined, 0, header.length);
        System.arraycopy(body, 0, combined, header.length, body.length);
        return combined;
    }

    public String getHeader(String headerName) {
        return headers.get(headerName);
    }
}
