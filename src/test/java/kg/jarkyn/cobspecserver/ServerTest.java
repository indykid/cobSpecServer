package kg.jarkyn.cobspecserver;

import kg.jarkyn.cobspecserver.doubles.ClientDouble;
import kg.jarkyn.cobspecserver.doubles.ListenerDouble;
import kg.jarkyn.cobspecserver.doubles.ResponseControllerDouble;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerTest {

    private ClientDouble client;
    private ResponseControllerDouble controller;

    @Before
    public void setUp() throws Exception {
        client = new ClientDouble();
    }

    @Test
    public void satisfiesReceivedRequest() {
        Server server = new Server(new ListenerDouble(client), controllerWithResponse("hello"));

        server.satisfyRequest();

        assertEquals("hello", client.getReceivedMessage());
    }

    @Test
    public void runsUntilStopped() {
        controller = controllerWithResponse("irrelevant");
        Server server = new Server(new ListenerDouble(client, 2), controller);

        server.run();

        assertEquals(2, controller.getTimesResponded());
    }

    private ResponseControllerDouble controllerWithResponse(String hello) {
        return new ResponseControllerDouble(hello);
    }
}
