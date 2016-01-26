package kg.jarkyn.server.Resource;

import kg.jarkyn.server.FileHelpers;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ResourceReaderTest {

    @Test
    public void returnsFileByteContent() {
        byte[] expectedContent = FileHelpers.plainFileByteContent();

        assertArrayEquals(expectedContent, ResourceReader.readFile(FileHelpers.plainFileFullPath));
    }

    @Test
    public void listsFolderContents() {
        assertEquals(FileHelpers.publicFolderListing, ResourceReader.readFolder(FileHelpers.publicFolderPath));
    }
}
