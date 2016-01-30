package kg.jarkyn.server.resource;

import kg.jarkyn.server.fixtures.PublicResourceFixture;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

public class PublicResourceTest {
    private String path = PublicResourceFixture.publicResourcePath;
    private String existingFileRequestPath = PublicResourceFixture.existingFileRequestPath;
    private String nonExistingFile = PublicResourceFixture.nonExistingFile;
    private String publicResourceRequestPath = PublicResourceFixture.publicResourceRequestPath;
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
        assertTrue(publicResource.isDirectory(publicResourceRequestPath));
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
        String requestPath = PublicResourceFixture.existingFileRequestPath;
        byte[] expectedContent = PublicResourceFixture.existingFileByteContent();

        assertArrayEquals(expectedContent, publicResource.readFile(requestPath));
    }

    @Test
    public void listsDirectoryContents() {
        String directoryPath = PublicResourceFixture.publicResourceRequestPath;

        assertEquals(Arrays.asList("file1", "file2"), publicResource.readDirectory(directoryPath));
    }
}
