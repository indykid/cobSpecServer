package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Responder;
import kg.jarkyn.server.outgoing.Response;
import kg.jarkyn.server.utils.PublicResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DELETEResponder extends Responder {
    private PublicResource publicResource;

    public DELETEResponder(PublicResource publicResource) {
        this.publicResource = publicResource;
    }

    @Override
    public Response respond(Request request) {
        deleteResource(request);
        return new Response(successStatusLine(), "", "".getBytes());
    }

    private void deleteResource(Request request) {
        try {
            Files.deleteIfExists(Paths.get(publicResource.fullPathFor(request.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
