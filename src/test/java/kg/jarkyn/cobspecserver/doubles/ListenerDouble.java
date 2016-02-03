package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.Client;
import kg.jarkyn.cobspecserver.Listener;

public class ListenerDouble implements Listener {
    private Client client;
    private int allowedConnections;
    private int acceptedConnections = 0;

    public ListenerDouble(ClientDouble client, int allowedConnections) {
        this.client = client;
        this.allowedConnections = allowedConnections;
    }

    public ListenerDouble(ClientDouble client) {
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
