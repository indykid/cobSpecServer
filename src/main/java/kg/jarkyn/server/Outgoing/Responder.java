package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.incoming.Request;

import static kg.jarkyn.server.Status.SUCCESS;

public abstract class Responder {
    public static String STATUS_LINE_TEMPLATE = "HTTP/1.1 %s %s";

    public abstract Response respond(Request request);

    public String successStatusLine() {
        return String.format(STATUS_LINE_TEMPLATE, SUCCESS.getCode(), SUCCESS.getDescription());
    }
}
