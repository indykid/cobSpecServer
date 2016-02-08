package kg.jarkyn.cobspecserver.utils;

import java.net.URLConnection;

public class ContentTypeDetector {
    public static String detect(String path) {
        String type = URLConnection.guessContentTypeFromName(path);
        if (type == null) {
            return "text/plain";
        }
        return type;
    }
}
