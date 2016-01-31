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
        Request request = parse(requester.getInputStream());
        Responder responder = chooseResponder(request);
        return getResponse(request, responder);
    }

    @Override
    public void sendResponse(Requester requester, Response response) {
        try {
            requester.getOutputStream().write(response.getByteContent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Request parse(InputStream inputStream) {
        return parser.parseRequestLine(inputStream);
    }


    public Responder chooseResponder(Request request) {
        return delegator.chooseResponder(request);
    }

    public Response getResponse(Request request, Responder responder) {
        return responder.respond(request);
    }
}
