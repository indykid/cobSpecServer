package kg.jarkyn.server;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PublicFolderTest {
    private String path = Helpers.projectFolder() + "/src/test/resources/public";
    private PublicFolder publicFolder;

    @Before
    public void setUp() throws Exception {
        publicFolder= new PublicFolder(path);
    }

    @Test
    public void returnsFullPublicPathFromRequestPath() {
        assertEquals(path + "/file_name", publicFolder.fullPathFor("/file_name"));
    }

    @Test
    public void fileIsNotPresent() {
        assertFalse(publicFolder.isPresent("/non_existent_file"));
    }

    @Test
    public void fileIsPresent() {
        assertTrue(publicFolder.isPresent("/file1"));
    }
}
