package kg.jarkyn.cobspecserver;

public class Server {
    private Listener listener;
    private ResponseController responseController;

    public Server(Listener listener, ResponseController responseController) {
        this.listener = listener;
        this.responseController = responseController;
    }

    public void run() {
        while (listener.isAccepting()) {
            satisfyRequest();
        }
    }

    public void satisfyRequest() {
        respond(listen());
    }

    private Client listen() {
        return listener.accept();
    }

    private void respond(Client client) {
        responseController.respond(client);
    }
}
