package kg.jarkyn.cobspecserver;

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

    @Before
    public void setUp() throws Exception {
        parser = new RequestParserDouble();
        router = new RouterDouble();
        responseController = new ResponseController(parser, router);
    }

    @Test
    public void parsesRequest() {
        responseController.respond(new ClientDouble());

        assertTrue(parser.hasParsed());
    }

    @Test
    public void routes() {
        responseController.respond(new ClientDouble());

        assertTrue(router.hasRouted());
    }

    @Test
    public void sendsResponse() {
        ClientDouble client = new ClientDouble();

        responseController.respond(client);

        assertTrue(client.hasReceived());
    }
}