package kg.jarkyn.server;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ResourceReaderTest {
    private ResourceReader reader;

    @Before
    public void setUp() throws Exception {
        reader = new ResourceReader();
    }

    @Test
    public void returnsFileByteContent() {
        byte[] expectedContent = Helpers.file1ByteContent();

        assertArrayEquals(expectedContent, reader.readFile(Helpers.publicFolder() + "/file1"));
    }

    @Test
    public void listsFolderContents() {
        List<String> expectedContent = Arrays.asList("file1", "file2");

        assertEquals(expectedContent, reader.readFolder(Helpers.publicFolder()));
    }
}
