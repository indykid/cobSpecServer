package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.Client;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class ClientDouble implements Client {

    private OutputStream outputStream;

    public ClientDouble() {
        this.outputStream = new ByteArrayOutputStream();
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
