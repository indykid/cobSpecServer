package kg.jarkyn.server.Resource;

import kg.jarkyn.server.FileHelpers;
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
        byte[] expectedContent = FileHelpers.plainFileByteContent();

        assertArrayEquals(expectedContent, reader.readFile(FileHelpers.plainFileFullPath));
    }

    @Test
    public void listsFolderContents() {
        assertEquals(FileHelpers.publicFolderListing, reader.readFolder(FileHelpers.publicFolderPath));
    }
}
