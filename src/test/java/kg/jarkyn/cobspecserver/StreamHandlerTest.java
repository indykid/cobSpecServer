package kg.jarkyn.cobspecserver;

import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class StreamHandlerTest {
    @Test
    public void writes() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        StreamHandler.write(outputStream, "message".getBytes());

        assertEquals("message", outputStream.toString());
    }
}