package kg.jarkyn.cobspecserver;

import kg.jarkyn.cobspecserver.doubles.ClientDouble;
import kg.jarkyn.cobspecserver.doubles.ListenerDouble;
import kg.jarkyn.cobspecserver.doubles.ResponsePreparerDouble;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerTest {

    @Test
    public void satisfiesReceivedRequest() {
        Client client = new ClientDouble();
        ListenerDouble listener = new ListenerDouble(client);
        Server server = new Server(listener, new ResponsePreparerDouble());

        server.satisfyRequest();

        assertEquals("hello", client.getOutputStream().toString());
    }

    @Test
    public void runsUntilStopped() {
        Client client = new ClientDouble();
        ListenerDouble listener = new ListenerDouble(client, 2);
        ResponsePreparerDouble responsePreparer = new ResponsePreparerDouble();
        Server server = new Server(listener, responsePreparer);

        server.run();

        assertEquals(2, responsePreparer.getTimesResponded());
    }
}
