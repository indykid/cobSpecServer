package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.utils.Status;

public class NotFoundResponder implements Responder {
    @Override
    public Response respond(Request request) {
        return new Response(Status.NOTFOUND);
    }
}
