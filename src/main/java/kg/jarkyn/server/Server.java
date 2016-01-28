package kg.jarkyn.server;

public class Server {
    private final ReceivingSocket serverSocket;
    private final Responder responder;

    public Server(ReceivingSocket serverSocket, Responder responder) {
        this.serverSocket = serverSocket;
        this.responder = responder;
    }

    public void run() {
        Requester requester = acceptConnection();
        Response response = prepareResponse(requester);
        sendResponse(requester, response);
    }

    private Requester acceptConnection() {
        return serverSocket.accept();
    }

    private Response prepareResponse(Requester clientSocket) {
        return responder.prepareResponse(clientSocket);
    }

    private void sendResponse(Requester requester, Response response) {
        responder.sendResponse(requester, response);
    }
}
