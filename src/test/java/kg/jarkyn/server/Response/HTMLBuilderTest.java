package kg.jarkyn.server.Response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTMLBuilderTest {

    @Test
    public void makesLink() {
        String link = "<a href=\"/file1\">file1</a>";

        assertEquals(link, HTMLBuilder.makeLink("/file1", "file1"));
    }
}
