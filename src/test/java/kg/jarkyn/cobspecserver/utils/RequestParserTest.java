package kg.jarkyn.cobspecserver.utils;

import kg.jarkyn.cobspecserver.data.Request;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    private String input = "POST /?AuthId=SOMEKEY&Action=SomeAction HTTP/1.1\r\n" +
                                 "Content-Length: 4\r\n" +
                                 "Accept: text/html\r\n\r\n" +
                                 "body";
    private ByteArrayInputStream inputStream;
    private RequestParser parser;
    private Request request;

    @Before
    public void setUp() throws Exception {
        parser = new RequestParser();
        inputStream = new ByteArrayInputStream(input.getBytes());
        request = parser.parse(inputStream);
    }

    @Test
    public void extractsMethod() {
        assertEquals("POST", request.getMethod());
    }

    @Test
    public void extractsPath() {
        assertEquals("/", request.getPath());
    }

    @Test
    public void extractsParams() {
        assertEquals(setupParams(), request.getParams());
    }

    @Test
    public void extractsHeaders() {
        assertEquals("4", request.getHeader("Content-Length"));
        assertEquals("text/html", request.getHeader("Accept"));
    }

    @Test
    public void extractsBody() {
        assertEquals("body", request.getBody());
    }

    @Test
    public void doesNotReadBodyWithoutContentLength() {
        input = "POST /?AuthId=SOMEKEY&Action=SomeAction HTTP/1.1\r\n" +
                "Accept: text/html\r\n\r\n" +
                "body";
        parser = new RequestParser();
        request = parser.parse(new ByteArrayInputStream(input.getBytes()));

        assertEquals("", request.getBody());
    }

    private Map<String, String> setupParams() {
        Map<String, String> params = new HashMap<>();
        params.put("AuthId", "SOMEKEY");
        params.put("Action", "SomeAction");
        return params;
    }
}
