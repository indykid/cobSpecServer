package kg.jarkyn.server.doubles;

import kg.jarkyn.server.incoming.Requester;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class RequestingSocketDouble implements Requester {
    private OutputStream outputStream = new ByteArrayOutputStream();

    @Override
    public InputStream getInputStream() {
        return null;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
