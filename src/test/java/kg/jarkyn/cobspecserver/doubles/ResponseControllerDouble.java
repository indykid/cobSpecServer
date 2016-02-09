package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.middleware.ResponseController;
import kg.jarkyn.cobspecserver.sockets.ClientSocket;
import kg.jarkyn.cobspecserver.utils.StreamHandler;

public class ResponseControllerDouble extends ResponseController {

    private String response;

    public ResponseControllerDouble(String response) {
        super(null, null);
        this.response = response;
    }

    public ResponseControllerDouble() {
        this("");
    }

    @Override
    public void respond(ClientSocket client) {
        StreamHandler.write(client.getOutputStream(), response.getBytes());
    }
}
