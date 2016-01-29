package kg.jarkyn.server.doubles;

import kg.jarkyn.server.incoming.ParsedRequest;
import kg.jarkyn.server.outgoing.Responder;
import kg.jarkyn.server.outgoing.Response;

public class ResponderDouble implements Responder {
    private boolean responding;

    public boolean isResponding() {
        return responding;
    }

    @Override
    public Response respond(ParsedRequest request) {
        responding = true;
        return new Response("response from server");
    }
}
