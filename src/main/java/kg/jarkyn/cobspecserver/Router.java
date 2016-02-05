package kg.jarkyn.cobspecserver;

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

    public void registerRoute(String path, Responder controller) {
        routes.put(path, controller);
    }

    private boolean isValidPath(String path) {
        return routes.containsKey(path);
    }
}
