package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;

import java.util.HashMap;
import java.util.Map;

import static kg.jarkyn.cobspecserver.utils.Status.REDIRECT;

public class RedirectResponder extends Responder {
    private static final String DOMAIN_URL = "http://localhost:5000";
    private Map<String, String> redirects = new HashMap();

    @Override
    public Response respond(Request request) {
        return new Response(status(), headers(request));
    }

    private String headers(Request request) {
        String path = request.getPath();
        return "Location: " + urlFor(redirects.get(path));
    }

    private String urlFor(String path) {
        return DOMAIN_URL + path;
    }

    private String status() {
        return String.format(STATUS_TEMPLATE, REDIRECT.getCode(), REDIRECT.getDescription());
    }

    public void registerRedirection(String from, String to) {
        redirects.put(from, to);
    }
}
