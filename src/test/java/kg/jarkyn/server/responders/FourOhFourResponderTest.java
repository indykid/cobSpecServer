package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FourOhFourResponderTest {
    @Test
    public void respondsWhenFileNotFound() {

    }

    @Test
    public void responds() {
        FourOhFourResponder responder = new FourOhFourResponder();

        assertEquals("HTTP/1.1 404 Not Found\r\n\r\n\r\n", responder.respond(new Request("GET", "/foobar", "")).getContent
                ());
    }
}