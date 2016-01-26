package kg.jarkyn.server.Resource;

import kg.jarkyn.server.FileHelpers;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

public class ResourceHandlerTest {

    private ResourceHandler resourceHandler;

    @Before
    public void setUp() throws Exception {
        resourceHandler = new ResourceHandler(new ResourceFolder(FileHelpers.publicFolderPath));
    }

    @Test
    public void resourceIsNotPresent() {
        assertFalse(resourceHandler.isPresent(FileHelpers.nonExistingFile));
    }

    @Test
    public void resourceIsPresent() {
        assertTrue(resourceHandler.isPresent(FileHelpers.plainFileRequestPath));
    }

    @Test
    public void resourceIsAFolder() {
        assertTrue(resourceHandler.isFolder(FileHelpers.publicFolderRequestPath));
    }

    @Test
    public void resourceIsNotAFolder() {
        assertFalse(resourceHandler.isFolder(FileHelpers.plainFileRequestPath));
    }

    @Test
    public void readsFileContentWhenGivenRequestPath() {
        byte[] content = resourceHandler.readFile(FileHelpers.plainFileRequestPath);

        assertArrayEquals(FileHelpers.plainFileByteContent(), content);
    }

    @Test
    public void listsFolderContents() {
        List<String> files = resourceHandler.readFolder(FileHelpers.publicFolderRequestPath);

        assertEquals(FileHelpers.publicFolderListing, files);
    }
}
