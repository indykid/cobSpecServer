package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.incoming.Request;

public abstract class Responder {
    public static final String DEFAULT_PROTOCOL = "HTTP/1.1";
    public abstract Response respond(Request request);
}
