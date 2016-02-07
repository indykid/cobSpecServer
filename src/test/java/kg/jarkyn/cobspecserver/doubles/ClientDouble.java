package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.ClientSocket;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ClientDouble extends ClientSocket {

    private OutputStream outputStream;

    public ClientDouble() {
        super(null);
        this.outputStream = new ByteArrayOutputStream();
    }

    @Override
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

    public String getReceivedMessage() {
        return outputStream.toString();
    }
}
