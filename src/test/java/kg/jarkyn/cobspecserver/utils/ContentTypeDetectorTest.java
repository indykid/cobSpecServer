package kg.jarkyn.cobspecserver.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContentTypeDetectorTest {

    @Test
    public void detectsWithoutFileExtension() {
        assertEquals("text/plain", ContentTypeDetector.detect("/file"));
    }

    @Test
    public void detectsWithFileExtension() {
        assertEquals("text/html", ContentTypeDetector.detect("/file.html"));
    }

    @Test
    public void detectsImages() {
        assertEquals("image/jpeg", ContentTypeDetector.detect("/some_name.jpeg"));
    }
}