package kg.jarkyn.server.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContentTypeDetectorTest {
    @Test
    public void detectsWithoutFileExtension() {
        assertEquals("text/plain", ContentTypeDetector.detect("/file.txt"));
    }

    @Test
    public void detectsJPEG() {
        assertEquals("image/jpeg", ContentTypeDetector.detect("/some_name.jpeg"));

    }
}
