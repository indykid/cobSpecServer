package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.Request;
import kg.jarkyn.cobspecserver.RequestParser;

import java.io.InputStream;

public class RequestParserDouble extends RequestParser {

    private boolean parsed;

    @Override
    public Request parse(InputStream inputStream) {
        parsed = true;
        return null;
    }

    public boolean hasParsed() {
        return parsed;
    }
}
