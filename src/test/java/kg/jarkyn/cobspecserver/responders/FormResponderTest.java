package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.utils.Status;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormResponderTest {

    private FormResponder responder;

    @Before
    public void setUp() throws Exception {
        responder = new FormResponder();
    }

    @Test
    public void respondsToPost() {
        Request request = new Request("POST", "irrelevant");

        Response response = responder.respond(request);

        assertEquals(Status.SUCCESS, response.getStatus());
    }

    @Test
    public void respondsToPut() {
        Request request = new Request("PUT", "irrelevant");

        Response response = responder.respond(request);

        assertEquals(Status.SUCCESS, response.getStatus());
    }
}
