package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PUTResponderTest {
    @Test
    public void responds() {
        PUTResponder responder = new PUTResponder();

        assertEquals("HTTP/1.1 200 OK\r\n\r\n\r\n", responder.respond(new Request("PUT", "/form", "")).getContent());
    }
}