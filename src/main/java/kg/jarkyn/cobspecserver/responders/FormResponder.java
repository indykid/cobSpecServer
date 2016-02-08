package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;

public class FormResponder extends Responder {
    @Override
    public Response respond(Request request) {
        return new Response(successStatus());
    }
}
