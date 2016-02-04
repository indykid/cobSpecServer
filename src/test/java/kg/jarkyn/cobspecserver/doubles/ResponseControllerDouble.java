package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.*;

import java.io.IOException;

public class ResponseControllerDouble extends ResponseController {

    private int timesResponded = 0;
    private String response;

    public ResponseControllerDouble(String response) {
        super(null, null);
        this.response = response;
    }

    @Override
    public void respond(Client client) {
        try {
            timesResponded++;
            client.getOutputStream().write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getTimesResponded() {
        return timesResponded;
    }
}
