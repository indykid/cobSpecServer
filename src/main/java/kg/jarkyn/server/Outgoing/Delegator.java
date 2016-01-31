package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.responders.*;
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

        } else if (isNotAllowed(request)) {
            return new MethodNotAllowedResponder();

        } else if (isDirectory(request.getPath())) {
            return new DirectoryListingResponder(publicResource);

        } else if (isPOST(request)) {
            return new POSTResponder();
        }
        return new FileReadResponder(publicResource);
    }

    private boolean isNotAllowed(Request request) {
        return !publicResource.isMethodAllowed(request.getMethod()) && Paths.isNotAllowed(request);
    }

    private boolean isPOST(Request request) {
        return request.getMethod().equals("POST");
    }

    private boolean isNotFound(Request request) {
        String requestPath = request.getPath();
        return !publicResource.contains(requestPath) && Paths.isNotFound(requestPath);
    }

    private boolean isDirectory(String requestPath) {
        return publicResource.isDirectory(requestPath);
    }

}
