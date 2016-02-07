package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormResponderTest {
    @Test
    public void responds() {
        FormResponder responder = new FormResponder();
        Request request = new Request("irrelevant", "irrelevant");

        Response response = responder.respond(request);

        assertEquals("HTTP/1.1 200 OK\r\n\r\n\r\n", response.getContent());
    }
}
