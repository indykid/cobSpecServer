package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.Responder;
import kg.jarkyn.cobspecserver.Response;

public class ResponderDouble implements Responder {
    @Override
    public Response respond() {
        return new Response("OK");
    }
}
