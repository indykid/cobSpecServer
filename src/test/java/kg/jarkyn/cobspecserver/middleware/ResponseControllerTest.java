package kg.jarkyn.cobspecserver.middleware;

import kg.jarkyn.cobspecserver.doubles.ClientDouble;
import kg.jarkyn.cobspecserver.doubles.RequestParserDouble;
import kg.jarkyn.cobspecserver.doubles.RouterDouble;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ResponseControllerTest {

    private RequestParserDouble parser;
    private RouterDouble router;
    private ResponseController responseController;
    private ClientDouble client;

    @Before
    public void setUp() throws Exception {
        client = new ClientDouble();
        parser = new RequestParserDouble();
        router = new RouterDouble();
        responseController = new ResponseController(parser, router);
    }

    @Test
    public void parsesRequest() {
        responseController.respond(client);

        assertTrue(parser.hasParsed());
    }

    @Test
    public void routes() {
        responseController.respond(client);

        assertTrue(router.hasRouted());
    }

    @Test
    public void sendsResponse() {
        responseController.respond(client);

        assertTrue(client.hasReceived());
    }

    @Test
    public void disconnects() {
        responseController.respond(client);

        assertTrue(client.isClosed());
    }
}