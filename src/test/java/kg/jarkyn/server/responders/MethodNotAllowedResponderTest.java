package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MethodNotAllowedResponderTest {
    @Test
    public void responds() {
        MethodNotAllowedResponder responder = new MethodNotAllowedResponder();
        String expected = "HTTP/1.1 405 Method Not Allowed\r\nAllow: GET\r\n\r\n";

        String actual = responder.respond(new Request("irrelevant", "irrelevant")).getContent();

        assertEquals(expected, actual);
    }
}