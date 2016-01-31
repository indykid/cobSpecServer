package kg.jarkyn.server.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static kg.jarkyn.server.utils.Methods.GET;
import static kg.jarkyn.server.utils.Methods.POST;

public class Paths {
    public static HashMap<String, List<Methods>> MAPPING = new HashMap<String, List<Methods>>(){
        {
            put("/form", Arrays.asList(GET, POST));
        }
    };


    public static boolean isNotFound(String requestPath) {
        return !MAPPING.containsKey(requestPath);
    }
}
