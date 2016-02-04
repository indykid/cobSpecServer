package kg.jarkyn.cobspecserver;

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
        Response response = responder.respond();
        sendResponse(client, response);
    }

    private void sendResponse(ClientSocket client, Response response) {
        StreamHandler.write(client.getOutputStream(), response.getBytes());
    }
}
