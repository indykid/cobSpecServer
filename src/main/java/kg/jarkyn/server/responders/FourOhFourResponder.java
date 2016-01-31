package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Responder;
import kg.jarkyn.server.outgoing.Response;

import static kg.jarkyn.server.utils.Status.NOTFOUND;

public class FourOhFourResponder extends Responder {
    @Override
    public Response respond(Request request) {
        return new Response(statusLine(), "", "".getBytes());
    }

    private String statusLine() {
        return String.format(STATUS_LINE_TEMPLATE, NOTFOUND.getCode(), NOTFOUND.getDescription());
    }
}
