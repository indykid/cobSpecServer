package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.fixtures.DirectoryFixture;
import kg.jarkyn.cobspecserver.utils.PublicResource;
import kg.jarkyn.cobspecserver.utils.Status;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

        response = responder.respond(request);

        assertEquals(Status.SUCCESS, response.getStatus());
        assertArrayEquals("file1 contents".getBytes(), response.getBody());
        assertTrue(response.getHeaders().contains("Content-Type: text/plain"));
    }

    @Test
    public void respondsWithDirectoryListing() {
        request = new Request("GET", "/");

        response = responder.respond(request);

        assertEquals(Status.SUCCESS, response.getStatus());
        assertArrayEquals(directoryLinks(), response.getBody());
        assertTrue(response.getHeaders().contains("Content-Type: text/html"));
    }

    @Test
    public void respondsWithNotFound() {
        request = new Request("GET", "/non_existing_resource");

        response = responder.respond(request);

        assertEquals(Status.NOTFOUND, response.getStatus());
    }

    private byte[] directoryLinks() {
        return ("<a href=\"/file1\">file1</a>" +
                "<a href=\"/file2\">file2</a>").getBytes();
    }
}