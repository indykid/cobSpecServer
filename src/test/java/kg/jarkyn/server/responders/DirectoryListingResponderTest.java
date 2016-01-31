package kg.jarkyn.server.responders;

import kg.jarkyn.server.fixtures.PublicDirectoryFixture;
import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.utils.PublicResource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectoryListingResponderTest {
    @Test
    public void responds() {
        PublicResource publicResource = new PublicResource(PublicDirectoryFixture.publicDirectoryPath);
        DirectoryListingResponder responder = new DirectoryListingResponder(publicResource);

        String response = responder.respond(new Request("GET", "/")).getContent();

        assertEquals(directoryListingResponse(), response);
    }

    private String directoryListingResponse() {
        String statusLine = "HTTP/1.1 200 OK\r\n";
        String headers = "" + "\r\n\r\n";
        String html = "<a href=\"/file1\">file1</a>" +
                      "<a href=\"/file2\">file2</a>" ;
        return (statusLine + headers + html);
    }
}