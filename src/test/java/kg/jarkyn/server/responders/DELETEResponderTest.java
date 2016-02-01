package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.utils.PublicResource;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static kg.jarkyn.server.fixtures.PublicDirectoryFixture.publicDirectoryPath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DELETEResponderTest {

    private PublicResource publicResource;

    @Before
    public void setUp() throws Exception {
        publicResource = new PublicResource(publicDirectoryPath);

        try {
            File file = new File(publicResource.fullPathFor("/form"));
            PrintWriter writer = new PrintWriter(file);
            writer.write("some content");
            writer.close();
            System.out.println(publicResource.contains("/form"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void responds() {
        DELETEResponder responder = new DELETEResponder(publicResource);
        Request request = new Request("DELETE", "/form", "");
        String expected = "HTTP/1.1 200 OK\r\n\r\n\r\n";

        assertEquals(expected, responder.respond(request).getContent());
        assertFalse(publicResource.contains("/form"));
    }
}