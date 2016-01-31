package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Responder;
import kg.jarkyn.server.outgoing.Response;
import kg.jarkyn.server.utils.PublicResource;
import kg.jarkyn.server.utils.HTMLMaker;

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
        String headers = "Content-Type: text/html";
        return new Response(successStatusLine(), headers, html.getBytes());
    }
}
