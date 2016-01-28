package kg.jarkyn.server.Outgoing;

import kg.jarkyn.server.Incoming.Requester;

public interface Responder {
    Response prepareResponse(Requester requester);
    void sendResponse(Requester requester, Response response);
}
