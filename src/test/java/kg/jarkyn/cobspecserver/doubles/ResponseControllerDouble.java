package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.ClientSocket;
import kg.jarkyn.cobspecserver.ResponseController;
import kg.jarkyn.cobspecserver.StreamHandler;

public class ResponseControllerDouble extends ResponseController {

    private int timesResponded = 0;
    private String response;

    public ResponseControllerDouble(String response) {
        super(null, null);
        this.response = response;
    }

    @Override
    public void respond(ClientSocket client) {
        timesResponded++;
        StreamHandler.write(client.getOutputStream(), response.getBytes());
    }

    public int getTimesResponded() {
        return timesResponded;
    }
}
