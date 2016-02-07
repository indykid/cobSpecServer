package kg.jarkyn.cobspecserver.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTMLMakerTest {
    @Test
    public void makesLink() {
        String expected = "<a href=\"/file1\">file1</a>";

        assertEquals(expected, HTMLMaker.makeLink("/file1", "file1"));
    }
}