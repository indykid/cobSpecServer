package kg.jarkyn.server.Fixtures;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class PublicResourceFixture {
    private static String projectDirectoryPath = System.getProperty("user.dir");
    public static String publicResourcePath = projectDirectoryPath + "/src/test/resources/public";
    public static String publicResourceRequestPath = "/";
    public static String existingFileRequestPath = "/file1";
    public static String nonExistingFile = "/non_existing_file";
    public static String existingFileFullPath = publicResourcePath + existingFileRequestPath;
    public static List<String> publicResourceListing = Arrays.asList("file1", "file2");

    public static byte[] existingFileByteContent() {
        Path file = new File(existingFileFullPath).toPath();
        try {
            return Files.readAllBytes(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}