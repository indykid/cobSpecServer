package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.fixtures.PublicDirectoryFixture;
import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.responders.*;
import kg.jarkyn.server.utils.PublicResource;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DelegatorTest {

    private Delegator delegator;

    @Before
    public void setUp() throws Exception {
        delegator = new Delegator(new PublicResource(PublicDirectoryFixture.publicDirectoryPath));
    }

    @Test
    public void returnsFileReadResponder() {
        Request request = new Request("GET", "/file1", "");

        assertTrue(delegator.chooseResponder(request) instanceof FileReadResponder);
    }

    @Test
    public void returnsDirectoryResponder() {
        Request request = new Request("GET", "/", "");

        assertTrue(delegator.chooseResponder(request) instanceof DirectoryListingResponder);
    }

    @Test
    public void returnsFourOhFourResponder() {
        Request request = new Request("GET", "/non_existent_path", "");

        assertTrue(delegator.chooseResponder(request) instanceof FourOhFourResponder);
    }

    @Test
    public void returnsPOSTResponder() {
        Request request = new Request("POST", "/form", "");

        assertTrue(delegator.chooseResponder(request) instanceof POSTResponder);
    }

    @Test
    public void returnsMethodNotAllowedResponder() {
        Request request = new Request("POST", "/file1", "");

        assertTrue(delegator.chooseResponder(request) instanceof MethodNotAllowedResponder);
    }

    @Test
    public void returnsPUTResponder() {
        Request request = new Request("PUT", "/form", "");

        assertTrue(delegator.chooseResponder(request) instanceof PUTResponder);
    }

    @Test
    public void returnsParamsDecodeResponder() {
        Request request = new Request("GET", "/parameters", "AuthId=SOMEKEY&Action=SomeAction");

        assertTrue(delegator.chooseResponder(request) instanceof ParamsDecodeResponder);
    }

    @Test
    public void returnsOptionsResponder() {
        Request request = new Request("OPTIONS", "/form", "");

        assertTrue(delegator.chooseResponder(request) instanceof OptionsResponder);
    }
}