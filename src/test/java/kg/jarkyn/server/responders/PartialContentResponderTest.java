package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Response;
import kg.jarkyn.server.utils.PublicResource;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;

import static kg.jarkyn.server.fixtures.PublicDirectoryFixture.file1FullPath;
import static kg.jarkyn.server.fixtures.PublicDirectoryFixture.publicDirectoryPath;
import static org.junit.Assert.assertEquals;

public class PartialContentResponderTest {
    @Test
    public void respondsToSpecifiedRange() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Range", "bytes=0-3");
        Request request = new Request("GET", "/file1", headers, "", "");
        String expected = "HTTP/1.1 206 Partial Content\r\n" +
                          "Content-Range: bytes 0-3/" + fileLength() + "\r\n" +
                          "Content-Length: 4\r\n\r\nfile";


        Response response = new PartialContentResponder(new PublicResource(publicDirectoryPath)).respond(request);
        assertEquals(expected, response.getContent());
    }

    @Test
    public void respondsToStartOfRange() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Range", "bytes=3-");
        Request request = new Request("GET", "/file1", headers, "", "");
        String expected = "HTTP/1.1 206 Partial Content\r\n" +
                          "Content-Range: bytes 3-13/" + fileLength() + "\r\n" +
                          "Content-Length: 11\r\n\r\ne1 contents";

        Response response = new PartialContentResponder(new PublicResource(publicDirectoryPath)).respond(request);

        assertEquals(expected, response.getContent());
    }

    @Test
    @Ignore
    public void respondsToEndOfRange() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Range", "bytes=-6");
        Request request = new Request("GET", "/file1", headers, "", "");
        String expected = "HTTP/1.1 206 Partial Content\r\n" +
                "Content-Range: bytes 8-13/" + fileLength() + "\r\n" +
                "Content-Length: 6\r\n\r\ne1 contents";

        Response response = new PartialContentResponder(new PublicResource(publicDirectoryPath)).respond(request);

        assertEquals(expected, response.getContent());
    }

    private String fileLength() {
        return String.valueOf(new File(file1FullPath).length());
    }

}