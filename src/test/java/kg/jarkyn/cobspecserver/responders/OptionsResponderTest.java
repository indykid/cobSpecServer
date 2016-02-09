package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.utils.Status;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OptionsResponderTest {

    @Test
    public void responds() {
        OptionsResponder responder = new OptionsResponder();

        Request request = new Request("irrelevant", "irrelevant");
        Response response = responder.respond(request);

        assertEquals(Status.SUCCESS, response.getStatus());
        assertEquals("Allow: GET,HEAD,POST,OPTIONS,PUT", response.getHeaders());
    }


}
