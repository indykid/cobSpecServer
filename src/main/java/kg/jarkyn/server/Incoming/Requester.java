package kg.jarkyn.server.incoming;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

public interface Requester extends Closeable {
    InputStream getInputStream();
    OutputStream getOutputStream();
}
