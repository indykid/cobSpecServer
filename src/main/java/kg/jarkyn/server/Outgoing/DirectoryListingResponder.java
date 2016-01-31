package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.resource.PublicResource;

import java.util.List;

public class DirectoryListingResponder extends Responder {
    private PublicResource publicResource;

    public DirectoryListingResponder(PublicResource publicResource) {
        this.publicResource = publicResource;
    }

    @Override
    public Response respond(Request request) {
        String html = "";
        List<String> files = publicResource.readDirectory(request.getPath());
        for (String fileName : files) {
            html += HTMLMaker.makeLink("/" + fileName, fileName);
        }
        String headers = "";
        return new Response(successStatusLine(), headers, html);
    }
}
