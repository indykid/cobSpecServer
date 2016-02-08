package kg.jarkyn.cobspecserver;

import kg.jarkyn.cobspecserver.middleware.ResponseController;
import kg.jarkyn.cobspecserver.sockets.ClientSocket;

public class Worker implements Runnable {
    private ClientSocket client;
    private ResponseController responseController;

    public Worker(ClientSocket client, ResponseController responseController) {
        this.client = client;
        this.responseController = responseController;
    }

    @Override
    public void run() {
        respondTo(client);
    }

    private void respondTo(ClientSocket client) {
        responseController.respond(client);
    }
}
