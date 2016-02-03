package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.*;

import java.io.IOException;

public class ResponsePreparerDouble extends ResponsePreparer {

    private int timesResponded = 0;

    @Override
    public void respond(Client client) {
        try {
            timesResponded++;
            client.getOutputStream().write("hello".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getTimesResponded() {
        return timesResponded;
    }
}
