package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.utils.ContentTypeDetector;
import kg.jarkyn.cobspecserver.utils.PublicResource;

public class PublicResourceResponder extends Responder {
    private PublicResource publicResource;

    public PublicResourceResponder(PublicResource publicResource) {
        this.publicResource = publicResource;
    }

    @Override
    public Response respond(Request request) {
        return new Response(successfulStatus(), headers(request), body(request));
    }

    private String headers(Request request) {
        return String.format("Content-Type: %s", ContentTypeDetector.detect(request.getPath()));
    }

    private byte[] body(Request request) {
        return publicResource.readFile(request.getPath());
    }
}
