package kg.jarkyn.server;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

public class ResourceHandlerTest {

    private ResourceHandler resourceHandler;
    private ResourceReader resourceReader = new ResourceReader();

    @Before
    public void setUp() throws Exception {
        resourceHandler = new ResourceHandler(new PublicFolder(FileHelpers.publicFolderPath), resourceReader);
    }

    @Test
    public void resourceIsNotPresent() {
        assertFalse(resourceHandler.isPresent(FileHelpers.nonExistingFile));
    }

    @Test
    public void resourceIsPresent() {
        assertTrue(resourceHandler.isPresent(FileHelpers.existingFileRequestPath));
    }

    @Test
    public void resourceIsAFolder() {
        assertTrue(resourceHandler.isFolder(FileHelpers.publicFolderRequestPath));
    }

    @Test
    public void resourceIsNotAFolder() {
        assertFalse(resourceHandler.isFolder(FileHelpers.existingFileRequestPath));
    }

    @Test
    public void readsFileContentWhenGivenRequestPath() {
        byte[] content = resourceHandler.readFile(FileHelpers.existingFileRequestPath);

        assertArrayEquals(FileHelpers.existingFileByteContent(), content);
    }

    @Test
    public void listsFolderContents() {
        List<String> files = resourceHandler.readFolder(FileHelpers.publicFolderRequestPath);

        assertEquals(FileHelpers.publicFolderListing, files);
    }
}
