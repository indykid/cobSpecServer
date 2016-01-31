package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.responders.DirectoryListingResponder;
import kg.jarkyn.server.responders.FileReadResponder;
import kg.jarkyn.server.responders.FourOhFourResponder;
import kg.jarkyn.server.responders.POSTResponder;
import kg.jarkyn.server.utils.Paths;
import kg.jarkyn.server.utils.PublicResource;

public class Delegator {

    private PublicResource publicResource;

    public Delegator(PublicResource publicResource) {
        this.publicResource = publicResource;
    }

    public Responder chooseResponder(Request request) {

        if (isNotFound(request)) {
            return new FourOhFourResponder();

        } else if (isDirectory(request.getPath())) {
            return new DirectoryListingResponder(publicResource);

        } else if (isPOST(request)) {
            return new POSTResponder();
        }
        return new FileReadResponder(publicResource);
    }

    private boolean isPOST(Request request) {
        return request.getMethod().equals("POST");
    }

    private boolean isNotFound(Request request) {
        String requestPath = request.getPath();
        return Paths.isNotFound(requestPath) && !publicResource.contains(requestPath);
    }

    private boolean isDirectory(String requestPath) {
        return publicResource.isDirectory(requestPath);
    }

}
