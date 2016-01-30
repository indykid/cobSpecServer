package kg.jarkyn.server.doubles;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Delegator;
import kg.jarkyn.server.outgoing.Responder;
import kg.jarkyn.server.resource.PublicResource;

public class DelegatorDouble extends Delegator {
    private boolean delegating;

    public DelegatorDouble() {
        super(new PublicResource("irrelevant"));
    }

    @Override
    public Responder chooseResponder(Request request) {
        delegating = true;
        return new ResponderDouble();
    }

    public boolean isDelegating() {
        return delegating;
    }
}
