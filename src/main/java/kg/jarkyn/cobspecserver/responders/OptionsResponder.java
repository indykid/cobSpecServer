package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.utils.Status;

import java.util.Arrays;
import java.util.List;

public class OptionsResponder implements Responder {
    private List<String> methods = Arrays.asList("GET", "HEAD", "POST", "OPTIONS", "PUT");

    @Override
    public Response respond(Request request) {
        return new Response(Status.SUCCESS, headers());
    }

    private String headers() {
        return "Allow: " + String.join(",", methods);
    }
}
