package kg.jarkyn.server.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PathsTest {
    @Test
    public void pathIsFound() {
        assertFalse(Paths.isNotFound("/form"));
    }

    @Test
    public void pathIsNotFound() {
        assertTrue(Paths.isNotFound("/non_existing_path"));
    }
}