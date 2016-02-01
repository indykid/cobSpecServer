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

public class POSTResponderTest {

    private POSTResponder responder;

    @Before
    public void setUp() throws Exception {
        responder = new POSTResponder(new PublicResource(publicDirectoryPath));
    }

    @Test
    public void responds() {
        assertEquals("HTTP/1.1 200 OK\r\n\r\n\r\n", responder.respond(new Request("POST", "/form", "")).getContent());
    }

    @Test
    public void createsResource() throws IOException {
        Response response = responder.respond(new Request("POST", "/form", new HashMap<>(), "new=content", ""));
        byte[] body = Files.readAllBytes(Paths.get(publicDirectoryPath + "/form"));

        assertArrayEquals("new=content".getBytes(), body);
        assertEquals("HTTP/1.1 200 OK\r\n\r\n\r\nnew=content", response.getContent());
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get(publicDirectoryPath + "/form"));
    }
}