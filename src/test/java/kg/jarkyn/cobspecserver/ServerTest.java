package kg.jarkyn.cobspecserver;

import kg.jarkyn.cobspecserver.doubles.ClientDouble;
import kg.jarkyn.cobspecserver.doubles.ListenerDouble;
import kg.jarkyn.cobspecserver.doubles.ResponseControllerDouble;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerTest {

    private ClientDouble client;

    @Before
    public void setUp() throws Exception {
        client = new ClientDouble();
    }

    @Test
    public void satisfiesReceivedRequest() {
        Server server = new Server(new ListenerDouble(client), new ResponseControllerDouble("hello"));

        server.satisfyRequest();

        assertEquals("hello", client.getOutputStream().toString());
    }

    @Test
    public void runsUntilStopped() {
        ResponseControllerDouble responseControllerDouble = new ResponseControllerDouble("irrelevant");
        Server server = new Server(new ListenerDouble(client, 2), responseControllerDouble);

        server.run();

        assertEquals(2, responseControllerDouble.getTimesResponded());
    }
}
