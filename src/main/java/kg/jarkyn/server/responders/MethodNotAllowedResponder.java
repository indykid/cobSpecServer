package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Responder;
import kg.jarkyn.server.outgoing.Response;

import static kg.jarkyn.server.utils.Status.NOTALLOWED;

public class MethodNotAllowedResponder extends Responder {
    @Override
    public Response respond(Request request) {
        String statusLine = String.format(STATUS_LINE_TEMPLATE, NOTALLOWED.getCode(), NOTALLOWED
                .getDescription());
        return new Response(statusLine, "Allow: GET", "".getBytes());
    }
}
