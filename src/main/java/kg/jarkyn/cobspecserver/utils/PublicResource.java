package kg.jarkyn.cobspecserver.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class PublicResource {
    private final String path;

    public PublicResource(String path) {
        this.path = path;
    }

    public String fullPathFor(String requestPath) {
        return path + requestPath;
    }

    public boolean contains(String requestPath) {
        return Files.exists(new File(fullPathFor(requestPath)).toPath());
    }

    public boolean isDirectory(String requestPath) {
        return new File(fullPathFor(requestPath)).isDirectory();
    }

    public byte[] readFile(String requestPath) {
        String fullPath = fullPathFor(requestPath);
        try {
            return Files.readAllBytes((new File(fullPath)).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> readDirectory(String requestPath) {
        return Arrays.asList(new File(fullPathFor(requestPath)).list());
    }
}
