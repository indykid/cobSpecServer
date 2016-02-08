package kg.jarkyn.cobspecserver.middleware;

import kg.jarkyn.cobspecserver.responders.Responder;
import kg.jarkyn.cobspecserver.data.Request;

import java.util.HashMap;
import java.util.Map;

public class Router {

    private Map<String, Responder> routes = new HashMap<>();
    private Responder defaultResponder;

    public Router(Responder defaultResponder) {
        this.defaultResponder = defaultResponder;
    }

    public Responder route(Request request) {
        if (isValidPath(request.getPath())) {
            return routes.get(request.getPath());
        } else {
            return defaultResponder;
        }
    }

    public void registerRoute(String path, Responder responder) {
        routes.put(path, responder);
    }

    private boolean isValidPath(String path) {
        return routes.containsKey(path);
    }
}
