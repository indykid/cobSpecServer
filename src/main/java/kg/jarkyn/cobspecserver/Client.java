package kg.jarkyn.cobspecserver;

import java.io.InputStream;
import java.io.OutputStream;

public interface Client {
    OutputStream getOutputStream();

    InputStream getInputStream();
}
