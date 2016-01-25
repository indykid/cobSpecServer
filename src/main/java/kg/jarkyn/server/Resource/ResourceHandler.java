package kg.jarkyn.server.Resource;

import java.io.File;
import java.util.List;

public class ResourceHandler {
    private ResourceFolder resourceFolder;
    private ResourceReader resourceReader;

    public ResourceHandler(ResourceFolder resourceFolder, ResourceReader resourceReader) {
        this.resourceFolder = resourceFolder;
        this.resourceReader = resourceReader;
    }

    public boolean isFolder(String requestPath) {
        return new File(resourceFolder.fullPathFor(requestPath)).isDirectory();
    }

    public boolean isPresent(String requestPath) {
        return resourceFolder.isPresent(requestPath);
    }

    public byte[] readFile(String requestPath) {
        return resourceReader.readFile(resourceFolder.fullPathFor(requestPath));
    }

    public List<String> readFolder(String requestPath) {
        return resourceReader.readFolder(resourceFolder.fullPathFor(requestPath));
    }
}
