package kg.jarkyn.cobspecserver;

import java.io.IOException;
import java.net.ServerSocket;

public class ListenerSocket implements Listener {
    private ServerSocket serverSocket;
    private boolean accepting = true;

    public ListenerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public Client accept() {
        try {
            return new ClientSocket(serverSocket.accept());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isAccepting() {
        return accepting;
    }

    public void stop() {
        accepting = false;
    }
}
