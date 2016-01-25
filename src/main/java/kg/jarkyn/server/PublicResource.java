package kg.jarkyn.server;

import java.io.File;
import java.nio.file.Files;

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
}
