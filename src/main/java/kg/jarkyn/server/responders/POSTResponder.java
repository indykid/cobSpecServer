package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Responder;
import kg.jarkyn.server.outgoing.Response;
import kg.jarkyn.server.utils.PublicResource;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class POSTResponder extends Responder {
    private PublicResource publicResource;

    public POSTResponder(PublicResource publicResource) {
        this.publicResource = publicResource;
    }

    @Override
    public Response respond(Request request) {
        File file = createResource(request);
        try {
            return new Response(successStatusLine(), "", Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File createResource(Request request) {
        try {
            File file = new File(publicResource.fullPathFor(request.getPath()));
            PrintWriter writer = new PrintWriter(file);
            writer.write(request.getBody());
            writer.close();
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
