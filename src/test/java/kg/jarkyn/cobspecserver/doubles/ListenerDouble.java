package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.sockets.ClientSocket;
import kg.jarkyn.cobspecserver.sockets.ListenerSocket;

public class ListenerDouble extends ListenerSocket {
    private ClientSocket client;
    private int allowedConnections;
    private int acceptedConnections = 0;

    public ListenerDouble(ClientSocket client, int allowedConnections) {
        super(null, true);
        this.client = client;
        this.allowedConnections = allowedConnections;
    }

    public ListenerDouble(ClientSocket client) {
        this(client, Integer.MAX_VALUE);
    }

    @Override
    public ClientSocket accept() {
        acceptedConnections++;
        return client;
    }

    @Override
    public boolean isAccepting() {
        return acceptedConnections < allowedConnections;
    }
}
