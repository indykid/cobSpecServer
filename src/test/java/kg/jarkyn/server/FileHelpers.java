package kg.jarkyn.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

class FileHelpers {
    private static String projectFolderPath = System.getProperty("user.dir");
    static String publicFolderPath = projectFolderPath + "/src/test/resources/public";
    static String publicFolderRequestPath = "/";
    static String existingFileRequestPath = "/file1";
    static String nonExistingFile = "/non_existing_file";
    static String existingFileFullPath = publicFolderPath + existingFileRequestPath;
    static List<String> publicFolderListing = Arrays.asList("file1", "file2");

    static byte[] existingFileByteContent() {
        Path file = new File(existingFileFullPath).toPath();
        try {
            return Files.readAllBytes(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
