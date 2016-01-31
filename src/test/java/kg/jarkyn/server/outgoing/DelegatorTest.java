package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.fixtures.PublicDirectoryFixture;
import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.responders.DirectoryListingResponder;
import kg.jarkyn.server.responders.FileReadResponder;
import kg.jarkyn.server.responders.FourOhFourResponder;
import kg.jarkyn.server.responders.POSTResponder;
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
        Request request = new Request("GET", "/file1");

        assertTrue(delegator.chooseResponder(request) instanceof FileReadResponder);
    }

    @Test
    public void returnsDirectoryResponder() {
        Request request = new Request("GET", "/");

        assertTrue(delegator.chooseResponder(request) instanceof DirectoryListingResponder);
    }

    @Test
    public void returnsFourOhFourResponder() {
        Request request = new Request("GET", "/non_existent_path");

        assertTrue(delegator.chooseResponder(request) instanceof FourOhFourResponder);
    }

    @Test
    public void returnsPostResponder() {
        Request request = new Request("POST", "/form");

        assertTrue(delegator.chooseResponder(request) instanceof POSTResponder);

    }
}