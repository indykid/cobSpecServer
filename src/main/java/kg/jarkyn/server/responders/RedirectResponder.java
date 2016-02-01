package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Responder;
import kg.jarkyn.server.outgoing.Response;

import static kg.jarkyn.server.utils.Status.REDIRECT;

public class RedirectResponder extends Responder {
    @Override
    public Response respond(Request request) {
        String status = String.format(STATUS_LINE_TEMPLATE, REDIRECT.getCode(), REDIRECT.getDescription
                ());
        String headers = "Location: http://localhost:5000/";
        return new Response(status, headers, "".getBytes());
    }
}
