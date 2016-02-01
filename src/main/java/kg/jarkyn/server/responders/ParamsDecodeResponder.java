package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Responder;
import kg.jarkyn.server.outgoing.Response;

public class ParamsDecodeResponder extends Responder {
    @Override
    public Response respond(Request request) {
        return new Response(successStatusLine(), "", request.getParams().getBytes());
    }
}
