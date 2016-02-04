package kg.jarkyn.cobspecserver;

import java.io.IOException;
import java.io.OutputStream;

public class StreamHandler {
    public static void write(OutputStream outputStream, byte[] message) {
        try {
            outputStream.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
