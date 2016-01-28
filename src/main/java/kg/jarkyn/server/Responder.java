package kg.jarkyn.server;

public interface Responder {
    Response prepareResponse(Requester requester);
    void sendResponse(Requester requester, Response response);
}
