package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.fixtures.PublicResourceFixture;
import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.resource.PublicResource;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class FileReadResponderTest {

    @Test
    public void responds() {
        PublicResource publicResource = new PublicResource(PublicResourceFixture.publicResourcePath);
        String requestPath = PublicResourceFixture.existingFileRequestPath;
        FileReadResponder responder = new FileReadResponder(publicResource);
        byte[] expected = "HTTP/1.1 200 OK\r\n\r\nfile1 contents".getBytes();

        assertArrayEquals(expected, responder.respond(new Request("GET", requestPath))
                .getContent());
    }
}