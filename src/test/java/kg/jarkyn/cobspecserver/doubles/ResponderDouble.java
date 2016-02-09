package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.responders.Responder;
import kg.jarkyn.cobspecserver.utils.Status;

import java.util.HashMap;

public class ResponderDouble implements Responder {

    @Override
    public Response respond(Request request) {
        return new Response(Status.SUCCESS, new HashMap<>(), "body".getBytes());
    }
}
