package kg.jarkyn.server.Resource;

import kg.jarkyn.server.FileHelpers;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResourceFolderTest {
    private String path = FileHelpers.publicFolderPath;
    private ResourceFolder resourceFolder;

    @Before
    public void setUp() throws Exception {
        resourceFolder = new ResourceFolder(path);
    }

    @Test
    public void returnsFullPublicPathFromRequestPath() {
        assertEquals(FileHelpers.plainFileFullPath, resourceFolder.fullPathFor(FileHelpers.plainFileRequestPath));
    }

    @Test
    public void fileIsNotPresent() {
        assertFalse(resourceFolder.isPresent(FileHelpers.nonExistingFile));
    }

    @Test
    public void fileIsPresent() {
        assertTrue(resourceFolder.isPresent(FileHelpers.plainFileRequestPath));
    }
}
