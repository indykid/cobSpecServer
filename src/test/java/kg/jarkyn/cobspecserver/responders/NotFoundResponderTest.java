package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.utils.Status;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NotFoundResponderTest {
    @Test
    public void responds() {
        Request request = new Request("irrelevant", "irrelevant");
        Responder responder = new NotFoundResponder();

        Response response = responder.respond(request);

        assertEquals(Status.NOTFOUND, response.getStatus());
    }
}