package kg.jarkyn.server.Resource;

import java.io.File;
import java.util.List;

public class ResourceHandler {
    private ResourceFolder resourceFolder;

    public ResourceHandler(ResourceFolder resourceFolder) {
        this.resourceFolder = resourceFolder;
    }

    public boolean isFolder(String requestPath) {
        return new File(resourceFolder.fullPathFor(requestPath)).isDirectory();
    }

    public boolean isPresent(String requestPath) {
        return resourceFolder.isPresent(requestPath);
    }

    public byte[] readFile(String requestPath) {
        return ResourceReader.readFile(resourceFolder.fullPathFor(requestPath));
    }

    public List<String> readFolder(String requestPath) {
        return ResourceReader.readFolder(resourceFolder.fullPathFor(requestPath));
    }
}
