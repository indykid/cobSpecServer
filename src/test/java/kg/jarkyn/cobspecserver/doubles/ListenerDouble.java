package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.Client;
import kg.jarkyn.cobspecserver.Listener;

public class ListenerDouble extends Listener {
    private Client client;
    private int allowedConnections;
    private int acceptedConnections = 0;

    public ListenerDouble(Client client, int allowedConnections) {
        this.client = client;
        this.allowedConnections = allowedConnections;
    }

    public ListenerDouble(Client client) {
        this(client, Integer.MAX_VALUE);
    }

    @Override
    public Client accept() {
        acceptedConnections++;
        return client;
    }

    @Override
    public boolean isAccepting() {
        return acceptedConnections < allowedConnections;
    }
}
