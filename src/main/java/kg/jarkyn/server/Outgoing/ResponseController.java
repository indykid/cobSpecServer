package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.incoming.*;

import java.io.IOException;
import java.io.InputStream;

public class ResponseController implements Controller {
    private RequestParser parser;
    private Delegator delegator;

    public ResponseController(RequestParser parser, Delegator delegator) {
        this.parser = parser;
        this.delegator = delegator;
    }

    @Override
    public Response prepareResponse(Requester requester) {
        ParsedRequest request = parse(requester.getInputStream());
        Responder responder = getResponder(request);
        return getResponse(request, responder);
    }

    @Override
    public void sendResponse(Requester requester, Response response) {
        try {
            requester.getOutputStream().write(response.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ParsedRequest parse(InputStream inputStream) {
        return parser.parse(inputStream);
    }


    public Responder getResponder(ParsedRequest request) {
        return delegator.allocateResponder(request);
    }

    public Response getResponse(ParsedRequest request, Responder responder) {
        return responder.respond(request);
    }
}
