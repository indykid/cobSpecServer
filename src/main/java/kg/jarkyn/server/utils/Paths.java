package kg.jarkyn.server.utils;

import kg.jarkyn.server.incoming.Request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Paths {
    public static HashMap<String, List<String>> MAPPING = new HashMap<String, List<String>>(){
        {
            put("/form", Arrays.asList("GET", "POST", "PUT"));
            put("/method_options", Arrays.asList("GET"));
        }
    };


    public static boolean isNotFound(String requestPath) {
        return !MAPPING.containsKey(requestPath);
    }

    public static boolean isNotAllowed(Request request) {
        List<String> allowedMethods = MAPPING.get(request.getPath());

        return isNotFound(request.getPath()) || !allowedMethods.contains(request.getMethod());
    }
}
