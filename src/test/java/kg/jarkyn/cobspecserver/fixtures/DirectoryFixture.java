package kg.jarkyn.cobspecserver.fixtures;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryFixture {
    private static String projectPath = System.getProperty("user.dir");
    public static String path = projectPath + "/src/test/resources/public";
    public static String file1Path = path + "/file1";

    public static byte[] fileByteContent() {
        Path file = new File(file1Path).toPath();
        try {
            return Files.readAllBytes(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
