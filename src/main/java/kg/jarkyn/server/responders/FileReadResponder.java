package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Responder;
import kg.jarkyn.server.outgoing.Response;
import kg.jarkyn.server.utils.ContentTypeDetector;
import kg.jarkyn.server.utils.PublicResource;

public class FileReadResponder extends Responder {
    private PublicResource publicResource;

    public FileReadResponder(PublicResource publicResource) {
        this.publicResource = publicResource;
    }

    @Override
    public Response respond(Request request) {
        byte[] body = publicResource.readFile(request.getPath());
        return new Response(successStatusLine(), headers(request), body);
    }

    private String headers(Request request) {
        return "Content-Type: " + ContentTypeDetector.detect(request.getPath());
    }
}
