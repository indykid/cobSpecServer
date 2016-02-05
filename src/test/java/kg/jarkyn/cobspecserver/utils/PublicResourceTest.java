package kg.jarkyn.cobspecserver.utils;

import kg.jarkyn.cobspecserver.fixtures.DirectoryFixture;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PublicResourceTest {
    private String path = DirectoryFixture.path;
    private String fileRequestPath = "/file1";
    private String nonExistingFile = "/non_existing_file";
    private String directoryRequestPath = "/";
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
        assertTrue(publicResource.contains(fileRequestPath));
    }

    @Test
    public void resourceIsADirectory() {
        assertTrue(publicResource.isDirectory(directoryRequestPath));
    }

    @Test
    public void resourceIsNotADirectory() {
        assertFalse(publicResource.isDirectory(fileRequestPath));
    }

    @Test
    public void nonExistingResourceIsNotADirectory() {
        assertFalse(publicResource.isDirectory(nonExistingFile));
    }

    @Test
    public void readsFile() {
        byte[] expectedContent = DirectoryFixture.fileByteContent();

        assertArrayEquals(expectedContent, publicResource.readFile(fileRequestPath));
    }

    @Test
    public void listsDirectoryContents() {
        assertEquals(Arrays.asList("file1", "file2"),publicResource.readDirectory(directoryRequestPath));
    }
}
