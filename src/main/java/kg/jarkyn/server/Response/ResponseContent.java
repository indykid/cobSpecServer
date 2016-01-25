package kg.jarkyn.server.Response;

public class ResponseContent {
    private byte[] byteContent;
    private String contentType;

    public ResponseContent(byte[] byteContent, String contentType) {

        this.byteContent = byteContent;
        this.contentType = contentType;
    }

    public byte[] getByteContent() {
        return byteContent;
    }

    public String getContentType() {
        return contentType;
    }
}
