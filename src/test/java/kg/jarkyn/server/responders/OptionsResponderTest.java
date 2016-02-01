package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OptionsResponderTest {
    @Test
    public void responds() {
        Request request = new Request("OPTIONS", "/method_options", "");
        String expected = "HTTP/1.1 200 OK\r\n" +
                          "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n\r\n";

        assertEquals(expected, new OptionsResponder().respond(request).getContent());
    }
}