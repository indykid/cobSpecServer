package kg.jarkyn.cobspecserver;

import kg.jarkyn.cobspecserver.doubles.ClientDouble;
import kg.jarkyn.cobspecserver.doubles.ExecutorServiceDouble;
import kg.jarkyn.cobspecserver.doubles.ListenerDouble;
import kg.jarkyn.cobspecserver.doubles.ResponseControllerDouble;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServerTest {

    private ClientDouble client;
    private ResponseControllerDouble controller;
    private ExecutorServiceDouble executor;

    @Before
    public void setUp() throws Exception {
        client = new ClientDouble();
        executor = new ExecutorServiceDouble();
        controller = new ResponseControllerDouble();
    }

    @Test
    public void satisfiesReceivedRequest() {
        Server server = new Server(executor, new ListenerDouble(client), controller);

        server.satisfyRequest();

        assertTrue(executor.hasExecuted());
        assertTrue(executor.getRunnable() instanceof Worker);
    }

    @Test
    public void runsUntilStopped() {
        Server server = new Server(executor, new ListenerDouble(client, 2), controller);

        server.run();

        assertEquals(2, executor.getTimesExecuted());
    }

}
