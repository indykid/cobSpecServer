package kg.jarkyn.server.incoming;

import java.io.IOException;
import java.net.ServerSocket;

public class ReceivingSocket {
    private ServerSocket serverSocket;

    public ReceivingSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Requester accept() {
        try {
            return new RequestingSocket(serverSocket.accept());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
