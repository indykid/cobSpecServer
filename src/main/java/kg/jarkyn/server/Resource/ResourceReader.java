package kg.jarkyn.server.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class ResourceReader {

    public static byte[] readFile(String filePath) {
        try {
            return Files.readAllBytes((new File(filePath)).toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static List<String> readFolder(String folderPath) {
        return Arrays.asList(new File(folderPath).list());
    }
}
