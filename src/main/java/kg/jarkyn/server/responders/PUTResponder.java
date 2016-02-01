package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Responder;
import kg.jarkyn.server.outgoing.Response;
import kg.jarkyn.server.utils.PublicResource;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PUTResponder extends Responder{
    private PublicResource publicResource;

    public PUTResponder(PublicResource publicResource) {
        this.publicResource = publicResource;
    }

    @Override
    public Response respond(Request request) {
        updateResource(request);
        return new Response(successStatusLine(), "", request.getBody().getBytes());
    }

    private void updateResource(Request request) {
        try {
            FileWriter fileWriter = new FileWriter(publicResource.fullPathFor(request.getPath()), true);
            PrintWriter writer = new PrintWriter(fileWriter);
            writer.write(request.getBody());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
