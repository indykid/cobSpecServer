package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RedirectResponderTest {
    @Test
    public void responds() {
        Request request = new Request("GET", "/redirect", "");
        String expected = "HTTP/1.1 302 Found\r\nLocation: http://localhost:5000/\r\n\r\n";

        assertEquals(expected, new RedirectResponder().respond(request).getContent());
    }
}