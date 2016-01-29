package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.incoming.Requester;

public interface Controller {
    Response prepareResponse(Requester requester);
    void sendResponse(Requester requester, Response response);
}
