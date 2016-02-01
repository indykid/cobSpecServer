package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.doubles.DelegatorDouble;
import kg.jarkyn.server.doubles.RequestParserDouble;
import kg.jarkyn.server.doubles.RequestingSocketDouble;
import kg.jarkyn.server.doubles.ResponderDouble;
import kg.jarkyn.server.incoming.Request;
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
    private Request request = new Request("request method", "requested/path", "");

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
        controller.chooseResponder(request);

        assertTrue(delegator.isDelegating());
    }

    @Test
    public void getsResponse() {
        ResponderDouble responder = new ResponderDouble();

        controller.getResponse(request, responder);

        assertTrue(responder.isResponding());
    }

    @Test
    public void preparesResponse() {
        RequestingSocketDouble requester = new RequestingSocketDouble();

        Response response = controller.prepareResponse(requester);

        assertEquals("status\r\nheaders\r\n\r\nbody", response.getContent());
    }

    @Test
    public void sendsResponse() {
        RequestingSocketDouble requester = new RequestingSocketDouble();
        Response response = new Response("status", "header", "body".getBytes());

        controller.sendResponse(requester, response);

        assertEquals(response.getContent(), requester.getOutputStream().toString());
    }
}