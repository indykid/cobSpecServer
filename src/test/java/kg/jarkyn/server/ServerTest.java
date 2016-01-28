package kg.jarkyn.server;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    private Server server;
    private ResponderDouble responder;
    private RequestingSocketDouble requestingSocket;
    private ReceivingSocketDouble serverSocket;

    @Test
    public void receivesAndRespondsToRequest() {
        requestingSocket = new RequestingSocketDouble();
        serverSocket = new ReceivingSocketDouble(requestingSocket);
        responder = new ResponderDouble();
        server = new Server(serverSocket, responder);

        server.run();

        ByteArrayOutputStream outputStream = (ByteArrayOutputStream) requestingSocket.getOutputStream();
        assertEquals("hello", new String(outputStream.toByteArray()));
    }

    private class ReceivingSocketDouble extends ReceivingSocket {
        private RequestingSocketDouble requestingSocket;

        public ReceivingSocketDouble(RequestingSocketDouble requestingSocket) {
            super(null);
            this.requestingSocket = requestingSocket;
        }

        public RequestingSocketDouble accept() {
            return requestingSocket;
        }
    }

    private class ResponderDouble implements Responder {
        public Response prepareResponse(Requester requester) {
            return new Response("hello");
        }

        @Override
        public void sendResponse(Requester requester, Response response) {
            try {
                requester.getOutputStream().write(response.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class RequestingSocketDouble implements Requester {
        private OutputStream outputStream = new ByteArrayOutputStream();

        @Override
        public InputStream getInputStream() {
            return null;
        }

        public OutputStream getOutputStream() {
            return outputStream;
        }
    }
}
