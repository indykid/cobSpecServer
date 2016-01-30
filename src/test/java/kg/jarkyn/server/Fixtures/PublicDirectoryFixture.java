package kg.jarkyn.server.fixtures;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PublicDirectoryFixture {
    private static String projectDirectoryPath = System.getProperty("user.dir");
    public static String publicDirectoryPath = projectDirectoryPath + "/src/test/resources/public";
    public static String file1FullPath = publicDirectoryPath + "/file1";

    public static byte[] file1ByteContent() {
        Path file = new File(file1FullPath).toPath();
        try {
            return Files.readAllBytes(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
