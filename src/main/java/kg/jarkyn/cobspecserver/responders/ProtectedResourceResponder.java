package kg.jarkyn.cobspecserver.responders;

import kg.jarkyn.cobspecserver.data.Request;
import kg.jarkyn.cobspecserver.data.Response;
import kg.jarkyn.cobspecserver.utils.Status;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ProtectedResourceResponder implements Responder {
    private String validUser;
    private String validPassword;

    public ProtectedResourceResponder(String validUser, String validPassword) {
        this.validUser = validUser;
        this.validPassword = validPassword;
    }

    @Override
    public Response respond(Request request) {
        if (isAuthenticated(request)) {
            return new Response(Status.SUCCESS, new HashMap<>(), setupBody(request));
        } else {
            return new Response(Status.UNAUTHORIZED, unauthorizedHeaders());
        }
    }

    private Map<String, String> unauthorizedHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("WWW-Authenticate", "Basic");
        return headers;
    }

    private byte[] setupBody(Request request) {
        return new byte[0];
    }

    private boolean isAuthenticated(Request request) {
        String credentials = decode(request.getHeader("Authorization"));
        if (!credentials.isEmpty() && credentials.contains(":")) {
            String username = extractUsername(credentials);
            String pass = extractPassword(credentials);
            return username.equals(validUser) && pass.equals(validPassword);
        } else {
            return false;
        }
    }

    private String decode(String authHeader) {
        if (authHeader != null) {
            return new String(Base64.getDecoder().decode(authHeader.getBytes()));
        } else {
            return "";
        }
    }

    private String extractUsername(String credentials) {
        return credentials.split(":", 2)[0];
    }

    private String extractPassword(String credentials) {
        return credentials.split(":", 2)[1];
    }
}
