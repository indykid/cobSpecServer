package kg.jarkyn.server;

import kg.jarkyn.server.Fixtures.PublicResourceFixture;
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
}
