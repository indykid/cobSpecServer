package kg.jarkyn.cobspecserver.data;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RequestTest {
    @Test
    public void returnsIndividualHeader() {
        Map<String, String> headers = new HashMap<>();
        headers.put("key", "value");
        Request request = new Request("GET", "protected_path", headers);

        assertEquals("value", request.getHeader("key"));
    }
}