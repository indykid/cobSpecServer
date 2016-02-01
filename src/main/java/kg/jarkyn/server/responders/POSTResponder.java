package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Responder;
import kg.jarkyn.server.outgoing.Response;
import kg.jarkyn.server.utils.PublicResource;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class POSTResponder extends Responder {
    private PublicResource publicResource;

    public POSTResponder(PublicResource publicResource) {
        this.publicResource = publicResource;
    }

    @Override
    public Response respond(Request request) {
        createResource(request);
        return new Response(successStatusLine(), "", "".getBytes());
    }

    private void createResource(Request request) {
        try {
            File file = new File(publicResource.fullPathFor(request.getPath()));
            PrintWriter writer = new PrintWriter(file);
            writer.write(request.getBody());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
