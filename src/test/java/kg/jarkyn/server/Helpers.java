package kg.jarkyn.server;

public class Helpers {
    static String projectFolder() {
        return System.getProperty("user.dir");
    }

    static String publicFolder() {
        return projectFolder() + "/src/test/resources/public";
    }

    static byte[] file1ByteContent() {
        return "file1 contents".getBytes();
    }
}
