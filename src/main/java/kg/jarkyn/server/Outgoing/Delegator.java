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

        } else if (isOPTIONS(request)) {
            return new OptionsResponder();

        } else if (isNotAllowed(request)) {
            return new MethodNotAllowedResponder();

        } else if (isDirectory(request.getPath())) {
            return new DirectoryListingResponder(publicResource);

        } else if (hasParams(request)) {
            return new ParamsDecodeResponder();

        } else if (isPOST(request)) {
            return new POSTResponder();

        } else if (isPUT(request)) {
            return new PUTResponder();
        }
        return new FileReadResponder(publicResource);
    }

    private boolean isOPTIONS(Request request) {
        return request.getMethod().equals("OPTIONS");
    }

    private boolean hasParams(Request request) {
        return !request.getParams().isEmpty();
    }

    private boolean isPUT(Request request) {
        return request.getMethod().equals("PUT");
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
