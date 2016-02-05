package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.Request;
import kg.jarkyn.cobspecserver.Responder;
import kg.jarkyn.cobspecserver.Router;

public class RouterDouble extends Router {
    private boolean routed;

    public RouterDouble() {
        super(null);
    }

    @Override
    public Responder route(Request request) {
        routed = true;
        return new ResponderDouble();
    }

    public boolean hasRouted() {
        return routed;
    }
}
