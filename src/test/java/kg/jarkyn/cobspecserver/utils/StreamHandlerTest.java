package kg.jarkyn.cobspecserver.utils;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StreamHandlerTest {
    @Test
    public void writes() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        StreamHandler.write(outputStream, "message".getBytes());

        assertEquals("message", outputStream.toString());
    }

    @Test
    public void returnsReader() {
        assertTrue(StreamHandler.setupReader(setupInputStream("irrelevant")) instanceof BufferedReader);
    }

    @Test
    public void readsSingleLine() {
        assertEquals("line1", StreamHandler.readLine(setupReader("line1\r\n" +
                                                                 "line2\r\n")));
    }

    @Test
    public void readsUntilEmptyLine() {
        List<String> expectedLines = Arrays.asList("line1", "line2");

        assertEquals(expectedLines,
                     StreamHandler.readUntilEmptyLine(setupReader("line1\r\n" +
                                                                  "line2\r\n" +
                                                                  "\r\n" +
                                                                  "body")));
    }

    @Test
    public void readsCharacters() {
        assertArrayEquals("body".toCharArray(), StreamHandler.readCharacters(setupReader("body"), 4));
    }

    private BufferedReader setupReader(String content) {
        return new BufferedReader(new InputStreamReader(setupInputStream(content)));
    }

    private InputStream setupInputStream(String content) {
        return new ByteArrayInputStream((content).getBytes());
    }
}