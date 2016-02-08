package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.utils.RequestParser;

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
