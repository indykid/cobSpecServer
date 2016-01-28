package kg.jarkyn.server.Incoming;

import java.io.InputStream;
import java.io.OutputStream;

public interface Requester {
    public InputStream getInputStream();
    public OutputStream getOutputStream();
}
