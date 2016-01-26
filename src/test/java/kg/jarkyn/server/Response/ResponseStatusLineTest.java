package kg.jarkyn.server.Response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseStatusLineTest {

    @Test
    public void returnsFormattedStatusLine() {
        ResponseStatusLine statusLine = new ResponseStatusLine("200");

        assertEquals("HTTP/1.1 200 OK", statusLine.format());
    }
}