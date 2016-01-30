package kg.jarkyn.server.outgoing;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ResponseTest {

    @Test
    public void returnsContent() {
        Response response = new Response("status", "headers", "body");
        byte[] expected = "status\r\nheaders\r\n\r\nbody".getBytes();

        assertArrayEquals(expected, response.getContent());
    }
}