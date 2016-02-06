package kg.jarkyn.cobspecserver.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ResponseTest {

    private Response response;

    @Before
    public void setUp() throws Exception {
        response = new Response("status", "headers", "body".getBytes());
    }

    @Test
    public void returnsContent() {
        assertEquals("status\r\nheaders\r\n\r\nbody", response.getContent());
    }

    @Test
    public void returnsByteContent() {
        assertArrayEquals("status\r\nheaders\r\n\r\nbody".getBytes(), response.getByteContent());
    }
}