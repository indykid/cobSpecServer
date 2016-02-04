package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.Client;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ClientDouble implements Client {

    private OutputStream outputStream;

    public ClientDouble() {
        this.outputStream = new ByteArrayOutputStream();
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public InputStream getInputStream() {
        return null;
    }

    public boolean hasReceived() {
        return !outputStream.toString().isEmpty();
    }
}
