package kg.jarkyn.cobspecserver.doubles;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.responders.Responder;
import kg.jarkyn.cobspecserver.middleware.Router;

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
