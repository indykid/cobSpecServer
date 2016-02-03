package kg.jarkyn.cobspecserver;

public class Server {
    private Listener listener;
    private ResponsePreparer responsePreparer;

    public Server(Listener listener, ResponsePreparer responsePreparer) {
        this.listener = listener;
        this.responsePreparer = responsePreparer;
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
        responsePreparer.respond(client);
    }
}
