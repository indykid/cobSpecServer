package kg.jarkyn.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class FileHelpers {
    private static String projectFolderPath = System.getProperty("user.dir");
    public static String publicFolderPath = projectFolderPath + "/src/test/resources/public";
    public static String publicFolderRequestPath = "/";
    static String plainFileName = "file1";
    static String anotherPlainFileName = "file2";
    public static String plainFileRequestPath = "/file1";
    static String anotherPlainFileRequestPath = "/file2";
    public static String nonExistingFile = "/non_existing_file";
    public static String plainFileFullPath = publicFolderPath + plainFileRequestPath;
    public static List<String> publicFolderListing = Arrays.asList("file1", "file2");

    static String publicFolderHTMLContent() {
        return "<a href=\"" + plainFileRequestPath + "\">" + plainFileName + "</a>" + "<a href=\"" +
                anotherPlainFileRequestPath + "\">" + anotherPlainFileName + "</a>";
    }

    public static byte[] publicFolderByteContent() {
        return publicFolderHTMLContent().getBytes();
    }

    public static byte[] plainFileByteContent() {
        Path file = new File(plainFileFullPath).toPath();
        try {
            return Files.readAllBytes(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
