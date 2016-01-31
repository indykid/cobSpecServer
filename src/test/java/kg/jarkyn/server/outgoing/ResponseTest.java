package kg.jarkyn.server.outgoing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseTest {

    @Test
    public void returnsContent() {
        Response response = new Response("status", "headers", "body".getBytes());

        assertEquals("status\r\nheaders\r\n\r\nbody", response.getContent());
    }
}