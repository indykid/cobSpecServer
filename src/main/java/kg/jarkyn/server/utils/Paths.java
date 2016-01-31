package kg.jarkyn.server.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Paths {
    public static HashMap<String, List<String>> MAPPING = new HashMap<String, List<String>>(){
        {
            put("/form", Arrays.asList("GET", "POST"));
        }
    };


    public static boolean isNotFound(String requestPath) {
        return !MAPPING.containsKey(requestPath);
    }
}
