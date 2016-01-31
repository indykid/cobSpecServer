package kg.jarkyn.server.outgoing;

import kg.jarkyn.server.fixtures.PublicDirectoryFixture;
import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.resource.PublicResource;
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
}