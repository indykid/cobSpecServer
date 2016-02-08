package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RedirectResponderTest {
    @Test
    public void responds() {
        RedirectResponder responder = new RedirectResponder("domain_url");
        responder.registerRedirection("/redirect", "/");
        Request request = new Request("GET", "/redirect");

        Response response = responder.respond(request);

        assertEquals("HTTP/1.1 302 Found\r\n" +
                     "Location: domain_url/\r\n\r\n", response.getContent());
    }
}
