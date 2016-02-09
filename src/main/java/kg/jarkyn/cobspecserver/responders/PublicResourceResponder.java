package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.utils.ContentTypeDetector;
import kg.jarkyn.cobspecserver.utils.HTMLMaker;
import kg.jarkyn.cobspecserver.utils.PublicResource;
import kg.jarkyn.cobspecserver.utils.Status;

import java.util.List;

public class PublicResourceResponder implements Responder {
    private PublicResource publicResource;
    private DirectoryResponder directoryResponder;
    private FileResponder fileResponder;
    private NotFoundResponder notFoundResponder;

    public PublicResourceResponder(PublicResource publicResource) {
        this.publicResource = publicResource;
        this.fileResponder = new FileResponder();
        this.directoryResponder = new DirectoryResponder();
        this.notFoundResponder = new NotFoundResponder();
    }

    @Override
    public Response respond(Request request) {
        String path = request.getPath();
        if (isDirectory(path)) {
            return directoryResponder.respond(request);
        } else if (isFound(path)) {
            return fileResponder.respond(request);
        } else {
            return notFoundResponder.respond(request);
        }
    }

    private boolean isFound(String path) {
        return publicResource.contains(path);
    }

    private boolean isDirectory(String path) {
        return publicResource.isDirectory(path);
    }

    private class FileResponder implements Responder {
        @Override
        public Response respond(Request request) {
            return new Response(Status.SUCCESS, headers(request), body(request));
        }

        private String headers(Request request) {
            return String.format("Content-Type: %s", ContentTypeDetector.detect(request.getPath()));
        }

        private byte[] body(Request request) {
            return publicResource.readFile(request.getPath());
        }
    }

    private class DirectoryResponder implements Responder {
        @Override
        public Response respond(Request request) {
            String html = "";
            List<String> files = publicResource.readDirectory(request.getPath());
            for (String fileName : files) {
                html += HTMLMaker.makeLink("/" + fileName, fileName);
            }
            String headers = "Content-Type: text/html";
            return new Response(Status.SUCCESS, headers, html.getBytes());
        }
    }
}
