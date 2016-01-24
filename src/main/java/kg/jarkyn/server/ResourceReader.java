package kg.jarkyn.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class ResourceReader {
    public byte[] readFile(String filePath) {
        try {
            return Files.readAllBytes((new File(filePath)).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> readFolder(String folderPath) {
        return Arrays.asList(new File(folderPath).list());
    }
}
