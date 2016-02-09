package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.utils.Status;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RedirectResponderTest {
    @Test
    public void responds() {
        RedirectResponder responder = new RedirectResponder("domain_url");
        responder.registerRedirection("/redirect", "/");
        Request request = new Request("GET", "/redirect");

        Response response = responder.respond(request);

        assertEquals(Status.REDIRECT, response.getStatus());
        assertEquals("domain_url/", response.getHeader("Location"));
    }
}
