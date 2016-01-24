package kg.jarkyn.server;

import java.io.File;
import java.nio.file.Files;

public class PublicFolder {

    private final String path;

    public PublicFolder(String path) {
        this.path = path;
    }

    public String fullPathFor(String requestPath) {
        return path + requestPath;
    }

    public boolean isPresent(String requestPath) {
        return Files.exists(new File(fullPathFor(requestPath)).toPath());
    }
}
