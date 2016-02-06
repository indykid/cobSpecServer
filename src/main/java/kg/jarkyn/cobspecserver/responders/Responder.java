package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;

public abstract class Responder {
    private static final String STATUS_TEMPLATE = "HTTP/1.1 %s %s";

    public abstract Response respond(Request request);

    public String successfulStatus() {
        return String.format(STATUS_TEMPLATE, 200, "OK");
    }
}
