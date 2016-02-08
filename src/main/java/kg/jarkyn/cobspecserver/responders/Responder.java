package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;

import static kg.jarkyn.cobspecserver.utils.Status.SUCCESS;

public abstract class Responder {
    static final String STATUS_TEMPLATE = "HTTP/1.1 %s %s";

    public abstract Response respond(Request request);

    public String successStatus() {
        return String.format(STATUS_TEMPLATE, SUCCESS.getCode(), SUCCESS.getDescription());
    }
}
