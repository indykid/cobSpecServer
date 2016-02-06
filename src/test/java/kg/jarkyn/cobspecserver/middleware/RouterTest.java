package kg.jarkyn.cobspecserver.middleware;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.doubles.ResponderDouble;
import kg.jarkyn.cobspecserver.responders.PublicResourceResponder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class RouterTest {

    private Router router;
    private ResponderDouble responder;

    @Before
    public void setUp() throws Exception {
        responder = new ResponderDouble();
        router = new Router(new PublicResourceResponder(null));
    }

    @Test
    public void returnsResponder() {
        router.registerRoute("/", responder);

        assertSame(responder, router.route(new Request("GET", "/")));
    }

    @Test
    public void fallsBackToDefaultResponder() {
        Request request = new Request("GET", "/");

        assertTrue(router.route(request) instanceof PublicResourceResponder);
    }
}