package kg.jarkyn.server.responders;

import kg.jarkyn.server.fixtures.PublicDirectoryFixture;
import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.utils.PublicResource;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class DirectoryListingResponderTest {
    @Test
    public void responds() {
        PublicResource publicResource = new PublicResource(PublicDirectoryFixture.publicDirectoryPath);
        DirectoryListingResponder responder = new DirectoryListingResponder(publicResource);

        assertArrayEquals(directoryListingResponse(), responder.respond(new Request("GET", "/")).getContent());
    }

    private byte[] directoryListingResponse() {
        String statusLine = "HTTP/1.1 200 OK";
        String headers = "" + "\r\n\r\n";
        String html = "<a href=\"/file1\">file1</a>" +
                      "<a href=\"/file2\">file2</a>" ;
        return (statusLine + headers + html).getBytes();
    }
}