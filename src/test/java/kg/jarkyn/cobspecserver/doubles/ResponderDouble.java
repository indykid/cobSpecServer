package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.responders.Responder;
import kg.jarkyn.cobspecserver.data.Response;

public class ResponderDouble extends Responder {

    @Override
    public Response respond(Request request) {
        return new Response("status", "headers", "body".getBytes());
    }
}
