package kg.jarkyn.server.utils;

import java.net.URLConnection;

public class ContentTypeDetector {

    public static String detect(String requestPath) {
        String type = URLConnection.guessContentTypeFromName(requestPath);
        if (type == null) {
            return "text/plain";
        }
        return type;
    }

}
