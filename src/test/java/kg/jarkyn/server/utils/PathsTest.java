package kg.jarkyn.server.utils;

import kg.jarkyn.server.incoming.Request;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PathsTest {
    @Test
    public void pathIsFound() {
        assertFalse(Paths.isNotFound("/form"));
    }

    @Test
    public void isNotFound() {
        assertTrue(Paths.isNotFound("/non_existing_path"));
    }

    @Test
    public void isNotAllowed() {
        assertTrue(Paths.isNotAllowed(new Request("POST", "/method_options", "")));
    }

    @Test
    public void isAllowed() {
        assertFalse(Paths.isNotAllowed(new Request("GET", "/method_options", "")));
    }
}