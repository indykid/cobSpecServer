package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NotFoundResponderTest {
    @Test
    public void responds() {
        Request request = new Request("irrelevant", "irrelevant");
        Responder responder = new NotFoundResponder();

        Response response = responder.respond(request);

        assertEquals("HTTP/1.1 404 Not Found\r\n\r\n\r\n", response.getContent());
    }
}