package kg.jarkyn.server;

import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertTrue;

public class ReceivingSocketTest {

    @Test
    public void listensForConnections() {
        ServerSocketDouble serverSocket = setupServerSocket(8888);
        ReceivingSocket receivingSocket = new ReceivingSocket(serverSocket);

        receivingSocket.accept();

        assertTrue(serverSocket.isAccepting());
    }

    @Test
    public void returnsRequestFromClientConnection() {
        ServerSocketDouble serverSocket = setupServerSocket(9999);
        ReceivingSocket receivingSocket = new ReceivingSocket(serverSocket);

        assertTrue(receivingSocket.accept() instanceof RequestingSocket);
    }

    private ServerSocketDouble setupServerSocket(int port) {
        try {
            return new ServerSocketDouble(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private class ServerSocketDouble extends ServerSocket {
        private boolean accepting = false;
        private int port;

        public ServerSocketDouble(int port) throws IOException {
            super(port);
            this.port = port;
        }

        @Override
        public Socket accept() {
            accepting = true;
            try {
                return new Socket("localhost", port);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean isAccepting() {
            return accepting;
        }
    }
}
