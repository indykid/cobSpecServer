package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.utils.Status;
import org.junit.Before;
import org.junit.Test;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProtectedResourceResponderTest {

    private ProtectedResourceResponder responder;
    private Map<String, String> requestHeaders;

    @Before
    public void setUp() throws Exception {
        responder = new ProtectedResourceResponder("admin", "password");
        requestHeaders = new HashMap<>();
    }

    @Test
    public void refusesAccessWithoutCredentials() {
        Request request = new Request("GET", "/protected_path");

        Response response = responder.respond(request);

        assertEquals(Status.UNAUTHORIZED, response.getStatus());
        assertTrue(response.getHeader("WWW-Authenticate").contains("Basic"));
    }

    @Test
    public void doesNotRespondToIncorrectCredentials() {
        requestHeaders.put("Authorization", setCredentials("user:pass"));
        Request request = new Request("GET", "/protected_path", requestHeaders);

        Response response = responder.respond(request);

        assertEquals(Status.UNAUTHORIZED, response.getStatus());

    }

    @Test
    public void respondsToCorrectCredentials() {
        requestHeaders.put("Authorization", setCredentials("admin:password"));
        Request request = new Request("GET", "/protected_path", requestHeaders);

        Response response = responder.respond(request);

        assertEquals(Status.SUCCESS, response.getStatus());
    }

    private String setCredentials(String credentials) {
        return new String(Base64.getEncoder().encode(credentials.getBytes()));
    }
}
