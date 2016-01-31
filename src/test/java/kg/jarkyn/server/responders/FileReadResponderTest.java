package kg.jarkyn.server.responders;

import kg.jarkyn.server.fixtures.PublicDirectoryFixture;
import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.utils.PublicResource;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class FileReadResponderTest {

    @Test
    public void responds() {
        PublicResource publicResource = new PublicResource(PublicDirectoryFixture.publicDirectoryPath);
        FileReadResponder responder = new FileReadResponder(publicResource);
        byte[] expected = "HTTP/1.1 200 OK\r\n\r\nfile1 contents".getBytes();

        assertArrayEquals(expected, responder.respond(new Request("GET", "/file1"))
                .getContent());
    }
}