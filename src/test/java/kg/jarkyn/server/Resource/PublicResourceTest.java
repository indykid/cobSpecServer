package kg.jarkyn.server.resource;

import kg.jarkyn.server.fixtures.PublicResourceFixture;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PublicResourceTest {
    private String path = PublicResourceFixture.publicResourcePath;
    private String existingFileRequestPath = PublicResourceFixture.existingFileRequestPath;
    private String nonExistingFile = PublicResourceFixture.nonExistingFile;
    private String existingFileFullPath = PublicResourceFixture.existingFileFullPath;
    private PublicResource publicResource;
    private String publicResourceRequestPath = PublicResourceFixture.publicResourceRequestPath;

    @Before
    public void setUp() throws Exception {
        publicResource = new PublicResource(path);
    }

    @Test
    public void returnsFullPublicPathFromRequestPath() {
        assertEquals(existingFileFullPath, publicResource.fullPathFor(existingFileRequestPath));
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
}
