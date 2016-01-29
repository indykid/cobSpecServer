package kg.jarkyn.server.doubles;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Delegator;
import kg.jarkyn.server.outgoing.Responder;

public class DelegatorDouble extends Delegator {
    private boolean delegating;

    @Override
    public Responder allocateResponder(Request request) {
        delegating = true;
        return new ResponderDouble();
    }

    public boolean isDelegating() {
        return delegating;
    }
}
