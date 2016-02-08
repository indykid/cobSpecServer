package kg.jarkyn.cobspecserver.utils;

import kg.jarkyn.cobspecserver.data.Request;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    private final String input = "POST /?AuthId=SOMEKEY&Action=SomeAction HTTP/1.1\r\n" +
                                 "Connection: keep-alive\r\n" +
                                 "Accept-Language: en-US,en\r\n" +
                                 "Accept: text/html\r\n\r\n" +
                                 "body";
    private final ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    private RequestParser parser;
    private Request request;

    @Before
    public void setUp() throws Exception {
        parser = new RequestParser();
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
}