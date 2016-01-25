package kg.jarkyn.server;

import java.io.File;
import java.util.List;

public class ResourceHandler {
    private PublicFolder publicFolder;
    private ResourceReader resourceReader;

    public ResourceHandler(PublicFolder publicFolder, ResourceReader resourceReader) {
        this.publicFolder = publicFolder;
        this.resourceReader = resourceReader;
    }

    public boolean isFolder(String requestPath) {
        return new File(publicFolder.fullPathFor(requestPath)).isDirectory();
    }

    public boolean isPresent(String requestPath) {
        return publicFolder.isPresent(requestPath);
    }

    public byte[] readFile(String requestPath) {
        return resourceReader.readFile(publicFolder.fullPathFor(requestPath));
    }

    public List<String> readFolder(String requestPath) {
        return resourceReader.readFolder(publicFolder.fullPathFor(requestPath));
    }
}
