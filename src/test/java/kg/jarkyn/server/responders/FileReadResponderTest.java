package kg.jarkyn.server.responders;

import kg.jarkyn.server.fixtures.PublicDirectoryFixture;
import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.utils.PublicResource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileReadResponderTest {

    @Test
    public void responds() {
        PublicResource publicResource = new PublicResource(PublicDirectoryFixture.publicDirectoryPath);
        FileReadResponder responder = new FileReadResponder(publicResource);
        String expected = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\nfile1 contents";

        assertEquals(expected, responder.respond(new Request("GET", "/file1"))
                .getContent());
    }
}