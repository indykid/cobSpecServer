package kg.jarkyn.cobspecserver;

import kg.jarkyn.cobspecserver.doubles.ClientDouble;
import kg.jarkyn.cobspecserver.doubles.ResponseControllerDouble;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WorkerTest {
    @Test
    public void respondsToGivenClient() {
        ClientDouble client = new ClientDouble();
        Worker worker = new Worker(client, controllerWithResponse("response"));

        worker.run();

        assertEquals("response", client.getReceivedMessage());
    }

    private ResponseControllerDouble controllerWithResponse(String hello) {
        return new ResponseControllerDouble(hello);
    }
}
