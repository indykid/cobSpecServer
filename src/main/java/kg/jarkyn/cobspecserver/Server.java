package kg.jarkyn.cobspecserver;

public class Server {
    private ListenerSocket listener;
    private ResponseController responseController;

    public Server(ListenerSocket listener, ResponseController responseController) {
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
        responseController.respond(client);
    }
}
