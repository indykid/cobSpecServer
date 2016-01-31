package kg.jarkyn.server;

import kg.jarkyn.server.incoming.ReceivingSocket;
import kg.jarkyn.server.incoming.Requester;
import kg.jarkyn.server.outgoing.Controller;
import kg.jarkyn.server.outgoing.Response;

import java.io.IOException;

public class Server {
    private final ReceivingSocket serverSocket;
    private final Controller controller;

    public Server(ReceivingSocket serverSocket, Controller controller) {
        this.serverSocket = serverSocket;
        this.controller = controller;
    }

    public void run() {
        while (true) {
            try (Requester requester = acceptConnection()) {
                Response response = prepareResponse(requester);
                sendResponse(requester, response);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Requester acceptConnection() {
        return serverSocket.accept();
    }

    private Response prepareResponse(Requester clientSocket) {
        return controller.prepareResponse(clientSocket);
    }

    private void sendResponse(Requester requester, Response response) {
        controller.sendResponse(requester, response);
    }
}
