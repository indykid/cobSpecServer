package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.responders.Responder;
import kg.jarkyn.cobspecserver.data.Response;

public class ResponderDouble implements Responder {
    @Override
    public Response respond() {
        return new Response("OK");
    }
}
