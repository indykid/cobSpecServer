package kg.jarkyn.server;

import kg.jarkyn.server.doubles.RequestingSocketDouble;
import kg.jarkyn.server.incoming.Requester;
import kg.jarkyn.server.outgoing.Controller;
import kg.jarkyn.server.outgoing.Response;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    private Server server;
    private ControllerDouble responder;
    private RequestingSocketDouble requestingSocket;
    private ReceivingSocketDouble serverSocket;

    @Test
    public void receivesAndRespondsToRequest() {
        requestingSocket = new RequestingSocketDouble();
        serverSocket = new ReceivingSocketDouble(requestingSocket);
        responder = new ControllerDouble();
        server = new Server(serverSocket, responder);

        server.run();

        ByteArrayOutputStream outputStream = (ByteArrayOutputStream) requestingSocket.getOutputStream();
        assertEquals("status\r\nheaders\r\n\r\nbody", outputStream.toString());
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

    private class ControllerDouble implements Controller {

        @Override
        public Response prepareResponse(Requester requester) {
            return new Response("status", "headers", "body");
        }

        @Override
        public void sendResponse(Requester requester, Response response) {
            try {
                requester.getOutputStream().write(response.getContent());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
