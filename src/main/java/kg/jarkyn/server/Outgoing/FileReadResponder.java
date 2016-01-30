package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.Status;
import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.resource.PublicResource;

public class FileReadResponder extends Responder {
    private PublicResource publicResource;

    public FileReadResponder(PublicResource publicResource) {

        this.publicResource = publicResource;
    }

    @Override
    public Response respond(Request request) {
        String headers = "";
        String body = new String(publicResource.readFile(request.getPath()));
        return new Response(formatStatusLine(), headers, body);
    }

    private String formatStatusLine() {
        return  DEFAULT_PROTOCOL + " " + Status.SUCCESS.getCode() + " " + Status.SUCCESS.getDescription();
    }
}
