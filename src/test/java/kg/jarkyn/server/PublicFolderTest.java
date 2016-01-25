package kg.jarkyn.server;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PublicFolderTest {
    private String path = FileHelpers.publicFolderPath;
    private PublicFolder publicFolder;

    @Before
    public void setUp() throws Exception {
        publicFolder= new PublicFolder(path);
    }

    @Test
    public void returnsFullPublicPathFromRequestPath() {
        assertEquals(FileHelpers.existingFileFullPath, publicFolder.fullPathFor(FileHelpers.existingFileRequestPath));
    }

    @Test
    public void fileIsNotPresent() {
        assertFalse(publicFolder.isPresent(FileHelpers.nonExistingFile));
    }

    @Test
    public void fileIsPresent() {
        assertTrue(publicFolder.isPresent(FileHelpers.existingFileRequestPath));
    }
}
