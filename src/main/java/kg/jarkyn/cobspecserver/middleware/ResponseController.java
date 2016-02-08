package kg.jarkyn.cobspecserver.middleware;

import kg.jarkyn.cobspecserver.responders.Responder;
import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.sockets.ClientSocket;
import kg.jarkyn.cobspecserver.utils.RequestParser;
import kg.jarkyn.cobspecserver.utils.StreamHandler;

public class ResponseController {
    private RequestParser parser;
    private Router router;

    public ResponseController(RequestParser parser, Router router) {
        this.parser = parser;
        this.router = router;
    }

    public void respond(ClientSocket client) {
        Request request = parser.parse(client.getInputStream());
        Responder responder = router.route(request);
        Response response = responder.respond(request);
        sendResponse(client, response);
        disconnect(client);
    }

    private void sendResponse(ClientSocket client, Response response) {
        StreamHandler.write(client.getOutputStream(), response.getByteContent());
    }

    private void disconnect(ClientSocket client) {
        client.close();
    }
}
