package kg.jarkyn.cobspecserver;

import kg.jarkyn.cobspecserver.doubles.ResponderDouble;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouterTest {

    private Router router;
    private ResponderDouble responder;

    @Before
    public void setUp() throws Exception {
        responder = new ResponderDouble();
        router = new Router(new PublicResourceResponder());
    }

    @Test
    public void returnsResponder() {
        router.registerRoute("/", responder);

        assertSame(responder, router.route(new Request("GET", "/")));
    }

    @Test
    public void defaultsToPublicResourceResponder() {
        Request request = new Request("GET", "/");

        assertTrue(router.route(request) instanceof PublicResourceResponder);
    }
}