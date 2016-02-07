package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;

import java.util.Arrays;
import java.util.List;

public class OptionsResponder extends Responder {
    private List<String> methods = Arrays.asList("GET", "HEAD", "POST", "OPTIONS", "PUT");

    @Override
    public Response respond(Request request) {
        return new Response(successStatus(), headers());
    }

    private String headers() {
        return "Allow: " + String.join(",", methods);
    }
}
