package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.responders.FileReadResponder;
import kg.jarkyn.server.utils.PublicResource;
import kg.jarkyn.server.responders.DirectoryListingResponder;

public class Delegator {

    private PublicResource publicResource;

    public Delegator(PublicResource publicResource) {
        this.publicResource = publicResource;
    }

    public Responder chooseResponder(Request request) {
        if (isDirectory(request.getPath())) {
            return new DirectoryListingResponder(publicResource);
        }
        return new FileReadResponder(publicResource);
    }

    private boolean isDirectory(String requestPath) {
        return publicResource.isDirectory(requestPath);
    }
}
