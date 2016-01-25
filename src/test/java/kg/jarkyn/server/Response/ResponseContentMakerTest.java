package kg.jarkyn.server.Response;

import kg.jarkyn.server.FileHelpers;
import kg.jarkyn.server.Resource.ResourceFolder;
import kg.jarkyn.server.Resource.ResourceHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ResponseContentMakerTest {
    private ResourceHandler resourceHandler= new ResourceHandler(new ResourceFolder(FileHelpers.publicFolderPath));
    private ResponseContentMaker contentMaker;

    @Before
    public void setUp() throws Exception {
        contentMaker = new ResponseContentMaker(resourceHandler);
    }

    @Test
    public void returnsResponseContentForTextFile() {
        ResponseContent expected = new ResponseContent(FileHelpers.plainFileByteContent(), "text/plain");

        ResponseContent actual = contentMaker.make(FileHelpers.plainFileRequestPath);

        assertArrayEquals(expected.getByteContent(), actual.getByteContent());
        assertEquals(expected.getContentType(), actual.getContentType());
    }

    @Test
    public void returnsResponseContentForFolder() {
        ResponseContent expected = new ResponseContent(FileHelpers.publicFolderByteContent(), "text/html");

        ResponseContent actual = contentMaker.make(FileHelpers.publicFolderRequestPath);

        assertArrayEquals(expected.getByteContent(), actual.getByteContent());
        assertEquals(expected.getContentType(), actual.getContentType());
    }

    @Test
    public void returnsEmptyContentForNonExistingResource() {
        ResponseContent expected = new ResponseContent(new byte[0], "text/plain");

        ResponseContent actual = contentMaker.make(FileHelpers.nonExistingFile);

        assertArrayEquals(expected.getByteContent(), actual.getByteContent());
        assertEquals(expected.getContentType(), actual.getContentType());
    }
}
