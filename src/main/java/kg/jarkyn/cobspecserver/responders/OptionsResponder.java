package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.utils.Status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionsResponder implements Responder {
    private List<String> methods = Arrays.asList("GET", "HEAD", "POST", "OPTIONS", "PUT");

    @Override
    public Response respond(Request request) {
        return new Response(Status.SUCCESS, headers());
    }

    private Map<String, String> headers() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Allow", String.join(",", methods));
        return headers;
    }
}
