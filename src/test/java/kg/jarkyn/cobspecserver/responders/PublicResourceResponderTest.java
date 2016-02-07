package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.fixtures.DirectoryFixture;
import kg.jarkyn.cobspecserver.utils.PublicResource;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PublicResourceResponderTest {


    private PublicResourceResponder responder;
    private PublicResource publicResource;
    private Response response;
    private Request request;

    @Before
    public void setUp() throws Exception {
        publicResource = new PublicResource(DirectoryFixture.path);
        responder = new PublicResourceResponder(publicResource);
    }

    @Test
    public void respondsWithFileContents() {
        request = new Request("GET", "/file1");
        String expected = "HTTP/1.1 200 OK\r\n" +
                          "Content-Type: text/plain\r\n\r\n" +
                          "file1 contents";

        response = responder.respond(request);

        assertEquals(expected, response.getContent());
    }

    @Test
    public void respondsWithDirectoryListing() {
        request = new Request("GET", "/");

        response = responder.respond(request);

        assertEquals(directoryListingResponse(), response.getContent());
    }

    private String directoryListingResponse() {
        String statusLine = "HTTP/1.1 200 OK\r\n";
        String headers = "Content-Type: text/html" + "\r\n\r\n";
        String html = "<a href=\"/file1\">file1</a>" +
                      "<a href=\"/file2\">file2</a>" ;
        return (statusLine + headers + html);
    }
}