package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.doubles.*;
import kg.jarkyn.server.incoming.ParsedRequest;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResponseControllerTest {

    private DelegatorDouble delegator;
    private RequestParserDouble parser;
    private ResponseController controller;
    private InputStream stream = new ByteArrayInputStream(new byte[0]);
    private ParsedRequest request = new ParsedRequest("request method", "requested/path");

    @Before
    public void setUp() throws Exception {
        parser = new RequestParserDouble();
        delegator = new DelegatorDouble();
        controller = new ResponseController(parser, delegator);
    }

    @Test
    public void parsesRequest() {
        controller.parse(stream);

        assertTrue(parser.isParsing());
    }

    @Test
    public void getsRelevantResponseMaker() {
        controller.getResponder(request);

        assertTrue(delegator.isDelegating());
    }

    @Test
    public void getsResponse() {
        ResponderDouble responder = new ResponderDouble();

        controller.getResponse(request, responder);

        assertTrue(responder.isResponding());
    }

    @Test
    public void sendsResponse() {
        Response response = new Response("some content");
        RequestingSocketDouble requester = new RequestingSocketDouble();

        controller.sendResponse(requester, response);

        assertEquals("some content", requester.getOutputStream().toString());
    }

    @Test
    public void preparesResponse() {
        String response = controller.prepareResponse(new RequestingSocketDouble()).getMessage();

        assertEquals("response from server", response);
    }
}