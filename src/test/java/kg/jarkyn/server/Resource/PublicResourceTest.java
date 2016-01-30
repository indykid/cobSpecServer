package kg.jarkyn.server.resource;

import kg.jarkyn.server.fixtures.PublicDirectoryFixture;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

public class PublicResourceTest {
    private String path = PublicDirectoryFixture.publicDirectoryPath;
    private String existingFileRequestPath = "/file1";
    private String nonExistingFile = "/non_existing_file";
    private String publicDirectoryRequestPath = "/";
    private PublicResource publicResource;

    @Before
    public void setUp() throws Exception {
        publicResource = new PublicResource(path);
    }

    @Test
    public void doesNotContainResource() {
        assertFalse(publicResource.contains(nonExistingFile));
    }

    @Test
    public void containsResource() {
        assertTrue(publicResource.contains(existingFileRequestPath));
    }

    @Test
    public void resourceIsADirectory() {
        assertTrue(publicResource.isDirectory(publicDirectoryRequestPath));
    }

    @Test
    public void resourceIsNotADirectory() {
        assertFalse(publicResource.isDirectory(existingFileRequestPath));
    }

    @Test
    public void nonExistingResourceIsNotADirectory() {
        assertFalse(publicResource.isDirectory(nonExistingFile));
    }

    @Test
    public void readsFile() {
        byte[] expectedContent = PublicDirectoryFixture.file1ByteContent();

        assertArrayEquals(expectedContent, publicResource.readFile(existingFileRequestPath));
    }

    @Test
    public void listsDirectoryContents() {
        assertEquals(Arrays.asList("file1", "file2"),publicResource.readDirectory(publicDirectoryRequestPath));
    }
}
