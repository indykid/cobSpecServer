package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.Client;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class ClientDouble extends Client {

    private OutputStream outputStream = new ByteArrayOutputStream();

    @Override
    public OutputStream getOutputStream() {
        return outputStream;
    }
}
