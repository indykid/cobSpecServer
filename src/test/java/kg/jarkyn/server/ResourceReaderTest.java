package kg.jarkyn.server;

import kg.jarkyn.server.Fixtures.PublicResourceFixture;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ResourceReaderTest {
    private ResourceReader reader;

    @Before
    public void setUp() throws Exception {
        reader = new ResourceReader();
    }

    @Test
    public void returnsFileByteContent() {
        byte[] expectedContent = PublicResourceFixture.existingFileByteContent();

        assertArrayEquals(expectedContent, reader.readFile(PublicResourceFixture.existingFileFullPath));
    }

    @Test
    public void listsFolderContents() {
        assertEquals(PublicResourceFixture.publicResourceListing, reader.readFolder(PublicResourceFixture.publicResourcePath));
    }
}
