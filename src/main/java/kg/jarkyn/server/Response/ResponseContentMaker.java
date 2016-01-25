package kg.jarkyn.server.Response;

import kg.jarkyn.server.Resource.ResourceHandler;

import java.util.List;

public class ResponseContentMaker {
    private final ResourceHandler resourceHandler;

    public ResponseContentMaker(ResourceHandler resourceHandler) {
        this.resourceHandler = resourceHandler;
    }

    public ResponseContent make(String requestPath) {
        if (resourceHandler.isPresent(requestPath)) {
            if (resourceHandler.isFolder(requestPath)) {
                return prepareFolderContent(requestPath);
            }
            return prepareFileContent(requestPath);
        } else {
            return contentNotFound();
        }
    }

    private ResponseContent contentNotFound() {
        return new ResponseContent(new byte[0], "text/plain");
    }

    private ResponseContent prepareFolderContent(String requestPath) {
        String html = "";
        List<String> files = resourceHandler.readFolder(requestPath);
        for (String fileName : files) {
            html += HTMLMaker.makeLink("/" + fileName, fileName);
        }
        return new ResponseContent(html.getBytes(), "text/html");
    }

    private ResponseContent prepareFileContent(String requestPath) {
        return new ResponseContent(resourceHandler.readFile(requestPath), "text/plain");
    }
}
