package kg.jarkyn.cobspecserver.data;

import kg.jarkyn.cobspecserver.utils.Status;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ResponseTest {

    private Response response;

    @Before
    public void setUp() throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("key", "value");
        response = new Response(Status.SUCCESS, headers, "body".getBytes());
    }

    @Test
    public void returnsFormattedContentAsBytes() {
        assertArrayEquals("HTTP/1.1 200 OK\r\nkey: value\r\n\r\nbody".getBytes(), response.getByteContent());
    }

    @Test
    public void returnsIndividualHeader() {
        assertEquals("value", response.getHeader("key"));
    }
}