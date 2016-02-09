package kg.jarkyn.cobspecserver;

import kg.jarkyn.cobspecserver.middleware.ResponseController;
import kg.jarkyn.cobspecserver.sockets.ClientSocket;
import kg.jarkyn.cobspecserver.sockets.ListenerSocket;

import java.util.concurrent.ExecutorService;

public class Server {
    private ExecutorService executor;
    private ListenerSocket listener;
    private ResponseController responseController;

    public Server(ExecutorService executor, ListenerSocket listener, ResponseController responseController) {
        this.executor = executor;
        this.listener = listener;
        this.responseController = responseController;
    }

    public void run() {
        while (listener.isAccepting()) {
            satisfyRequest();
        }
    }

    public void satisfyRequest() {
        respondTo(nextClient());
    }

    private ClientSocket nextClient() {
        return listener.accept();
    }

    private void respondTo(ClientSocket client) {
        executor.execute(new Worker(client, responseController));
    }
}
