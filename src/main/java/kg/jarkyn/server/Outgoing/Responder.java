package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.incoming.ParsedRequest;

public interface Responder {
    Response respond(ParsedRequest request);
}
