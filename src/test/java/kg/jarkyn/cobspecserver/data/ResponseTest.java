package kg.jarkyn.cobspecserver.data;

import kg.jarkyn.cobspecserver.utils.Status;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ResponseTest {

    @Test
    public void returnsFormattedContentAsBytes() {
        Response response = new Response(Status.SUCCESS, "headers", "body".getBytes());

        assertArrayEquals("HTTP/1.1 200 OK\r\nheaders\r\n\r\nbody".getBytes(), response.getByteContent());
    }
}