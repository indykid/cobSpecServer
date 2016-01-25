package kg.jarkyn.server.Resource;

import java.io.File;
import java.nio.file.Files;

public class ResourceFolder {

    private final String path;

    public ResourceFolder(String path) {
        this.path = path;
    }

    public String fullPathFor(String requestPath) {
        return path + requestPath;
    }

    public boolean isPresent(String requestPath) {
        return Files.exists(new File(fullPathFor(requestPath)).toPath());
    }
}
