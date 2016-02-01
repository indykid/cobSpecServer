package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Response;
import kg.jarkyn.server.utils.PublicResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static kg.jarkyn.server.fixtures.PublicDirectoryFixture.publicDirectoryPath;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PUTResponderTest {
    private PUTResponder responder;

    @Before
    public void setUp() throws Exception {
        responder = new PUTResponder(new PublicResource(publicDirectoryPath));
    }

    @Test
    public void responds() {
        Response response = responder.respond(new Request("PUT", "/form", ""));

        assertEquals("HTTP/1.1 200 OK\r\n\r\n\r\n", response.getContent());
    }

    @Test
    public void updatesResource() throws IOException {
        responder.respond(new Request("PUT", "/form", new HashMap<>(), "new=content", ""));
        byte[] body = Files.readAllBytes(Paths.get(publicDirectoryPath + "/form"));

        assertArrayEquals("new=content".getBytes(), body);
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get(publicDirectoryPath + "/form"));
    }
}