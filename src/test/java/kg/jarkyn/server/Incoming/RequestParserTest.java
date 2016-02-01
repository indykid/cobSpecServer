package kg.jarkyn.server.incoming;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {

    private final String input = "POST /?AuthId=SOMEKEY&Action=SomeAction HTTP/1.1\r\n" +
                                 "Connection: keep-alive\r\n" +
                                 "Accept-Language: en-US,en\r\n" +
                                 "Accept: text/html\r\n\r\n" +
                                 "body";
    private InputStream inputStream = new ByteArrayInputStream(input.getBytes());

    @Test
    public void parsesRequestLine() {
        Request request = new RequestParser().parseRequestLine(inputStream);

        assertEquals("POST", request.getMethod());
        assertEquals("/", request.getPath());
        assertEquals("AuthId = SOMEKEY\nAction = SomeAction\n", request.getParams());
    }

    @Test
    public void parsesRequest() {
        Request request = new RequestParser().parse(inputStream);

        assertEquals("POST", request.getMethod());
        assertEquals("/", request.getPath());
        assertEquals("AuthId = SOMEKEY\nAction = SomeAction\n", request.getParams());
        assertEquals("body", request.getBody());
        assertEquals(headersMap(), request.getHeaders());

    }

    @Test
    public void parsesEncodedParams() {
        String input = "GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20" +
                "*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2" +
                "=stuff HTTP/1.1";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Request request = new RequestParser().parseRequestLine(inputStream);

        assertEquals(paramsWithEntities(), request.getParams());
    }

    private HashMap<String, String> headersMap() {
        return new HashMap<String, String>(){
            {
                put("Connection", "keep-alive");
                put("Accept-Language", "en-US,en");
                put("Accept", "text/html");
            }
        };
    }

    private String paramsWithEntities() {
        return "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?\nvariable_2 = stuff\n";
    }
}
