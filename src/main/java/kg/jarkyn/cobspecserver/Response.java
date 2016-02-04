package kg.jarkyn.cobspecserver;

public class Response {
    private String status;

    public Response(String status) {
        this.status = status;
    }

    public byte[] getBytes() {
        return status.getBytes();
    }
}
