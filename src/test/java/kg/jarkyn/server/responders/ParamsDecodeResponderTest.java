package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParamsDecodeResponderTest {
    @Test
    public void responds() {
        Request request = new Request("GET", "/parameters", "AuthId = SOMEKEY\nAction = SomeAction\n");
        String expected = "HTTP/1.1 200 OK\r\n\r\n\r\nAuthId = SOMEKEY\nAction = SomeAction\n";

        assertEquals(expected, new ParamsDecodeResponder().respond(request).getContent());
    }
}