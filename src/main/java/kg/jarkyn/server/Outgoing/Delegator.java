package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.resource.PublicResource;

public class Delegator {

    private PublicResource publicResource;

    public Delegator(PublicResource publicResource) {
        this.publicResource = publicResource;
    }

    public Responder chooseResponder(Request request) {
        return null;
    }
}
