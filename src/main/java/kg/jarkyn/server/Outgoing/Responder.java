package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.incoming.Request;

public interface Responder {
    Response respond(Request request);
}
