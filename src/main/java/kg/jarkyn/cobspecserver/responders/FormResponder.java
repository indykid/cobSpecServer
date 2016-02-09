package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.utils.Status;

public class FormResponder implements Responder {
    @Override
    public Response respond(Request request) {
        return new Response(Status.SUCCESS);
    }
}
