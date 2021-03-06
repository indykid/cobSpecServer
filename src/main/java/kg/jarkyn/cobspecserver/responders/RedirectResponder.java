package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.utils.Status;

import java.util.HashMap;
import java.util.Map;

public class RedirectResponder implements Responder {
    private Map<String, String> redirects = new HashMap();
    private String domainUrl;

    public RedirectResponder(String domainUrl) {
        this.domainUrl = domainUrl;
    }

    @Override
    public Response respond(Request request) {
        return new Response(Status.REDIRECT, headers(request));
    }

    private Map<String, String> headers(Request request) {
        Map<String, String> headers = new HashMap();
        String path = request.getPath();
        headers.put("Location", urlFor(redirects.get(path)));
        return headers;
    }

    private String urlFor(String path) {
        return domainUrl + path;
    }

    public void registerRedirection(String from, String to) {
        redirects.put(from, to);
    }
}
