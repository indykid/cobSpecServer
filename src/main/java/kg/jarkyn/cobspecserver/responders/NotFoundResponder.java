package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;

import static kg.jarkyn.cobspecserver.utils.Status.NOTFOUND;

public class NotFoundResponder extends Responder {
    @Override
    public Response respond(Request request) {
        return new Response(status());
    }

    private String status() {
        return String.format(STATUS_TEMPLATE, NOTFOUND.getCode(), NOTFOUND.getDescription());
    }
}
