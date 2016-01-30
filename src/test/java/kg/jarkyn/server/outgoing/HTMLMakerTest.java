package kg.jarkyn.server.outgoing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTMLMakerTest {
    @Test
    public void makesLink() {
        String link = "<a href=\"/file1\">file1</a>";

        assertEquals(link, HTMLMaker.makeLink("/file1", "file1"));
    }

}