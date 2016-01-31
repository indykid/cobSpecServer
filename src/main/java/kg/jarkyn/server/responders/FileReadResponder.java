package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Responder;
import kg.jarkyn.server.outgoing.Response;
import kg.jarkyn.server.utils.ContentTypeDetector;
import kg.jarkyn.server.utils.PublicResource;

import static kg.jarkyn.server.utils.Status.NOTALLOWED;

public class FileReadResponder extends Responder {
    private PublicResource publicResource;

    public FileReadResponder(PublicResource publicResource) {

        this.publicResource = publicResource;
    }

    @Override
    public Response respond(Request request) {
        if (isMethodAllowed(request)) {
            return respondWithSuccess(request);
        } else {
            return respondWithMethodNotAllowed();
        }
    }

    private Response respondWithMethodNotAllowed() {
        String statusLine = String.format(STATUS_LINE_TEMPLATE, NOTALLOWED.getCode(), NOTALLOWED
                .getDescription());
        return new Response(statusLine, "Allow: GET", "".getBytes());
    }

    private Response respondWithSuccess(Request request) {
        byte[] body = publicResource.readFile(request.getPath());
        return new Response(successStatusLine(), headers(request), body);
    }

    private boolean isMethodAllowed(Request request) {
        return publicResource.isMethodAllowed(request.getMethod());
    }

    private String headers(Request request) {
        return "Content-Type: " + ContentTypeDetector.detect(request.getPath());
    }
}
