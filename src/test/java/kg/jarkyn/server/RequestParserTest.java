package kg.jarkyn.server;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {

    private final String input = "GET /index.html HTTP/1.1\n" +
                                 "User-Agent: Mozilla/5.0 (Macintosh; " +
                                 "Intel Mac OS X 10.8; rv:20.0)\n" +
                                 " Gecko/20100101 Firefox/20.0\n" +
                                 "Host: en.wikipedia.org\n" +
                                 "Connection: keep-alive\n" +
                                 "Accept-Language: en-US,en;q=0.5\n" +
                                 "Accept-Encoding: gzip, deflate\n" +
                                 "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
    private InputStream inputStream = new ByteArrayInputStream(input.getBytes());

    @Test
    public void parsesRequestCorrectly() throws IOException {
        ParsedRequest parsedRequest = RequestParser.parse(inputStream);

        assertEquals("GET", parsedRequest.getVerb());
        assertEquals("/index.html", parsedRequest.getPath());
    }
}
