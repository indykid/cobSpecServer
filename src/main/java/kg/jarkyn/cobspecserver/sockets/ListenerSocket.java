package kg.jarkyn.cobspecserver.sockets;

import java.io.IOException;
import java.net.ServerSocket;

public class ListenerSocket {
    private ServerSocket serverSocket;
    private boolean accepting;

    public ListenerSocket(ServerSocket serverSocket, boolean accepting) {
        this.serverSocket = serverSocket;
        this.accepting = accepting;
    }

    public ClientSocket accept() {
        try {
            return new ClientSocket(serverSocket.accept());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isAccepting() {
        return accepting;
    }

    public void stop() {
        accepting = false;
    }
}
