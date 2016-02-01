package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Responder;
import kg.jarkyn.server.outgoing.Response;
import kg.jarkyn.server.utils.Paths;

import java.util.List;

public class OptionsResponder extends Responder {
    @Override
    public Response respond(Request request) {
        List<String> options = Paths.MAPPING.get(request.getPath());
        String headerValue = String.join(",", options);
        return new Response(successStatusLine(), "Allow: " + headerValue, "".getBytes());
    }
}
