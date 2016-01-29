package kg.jarkyn.server.doubles;

import kg.jarkyn.server.incoming.ParsedRequest;
import kg.jarkyn.server.incoming.RequestParser;

import java.io.InputStream;

public class RequestParserDouble extends RequestParser {
    private boolean parsing = false;

    public boolean isParsing() {
        return parsing;
    }

    public ParsedRequest parse(InputStream inputStream) {
        parsing = true;
        return new ParsedRequest("some method", "some/path");
    }
}
