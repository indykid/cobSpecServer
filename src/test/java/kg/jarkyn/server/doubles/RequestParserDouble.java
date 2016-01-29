package kg.jarkyn.server.doubles;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.incoming.RequestParser;

import java.io.InputStream;

public class RequestParserDouble extends RequestParser {
    private boolean parsing = false;

    public boolean isParsing() {
        return parsing;
    }

    public Request parse(InputStream inputStream) {
        parsing = true;
        return new Request("some method", "some/path");
    }
}
