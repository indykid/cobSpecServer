package kg.jarkyn.cobspecserver.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StreamHandler {
    public static void write(OutputStream outputStream, byte[] message) {
        try {
            outputStream.write(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readUntilEmptyLine(BufferedReader reader) {
        List<String> lines = new ArrayList<>();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    break;
                }
                lines.add(line);
            }
            return lines;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedReader setupReader(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public static char[] readCharacters(BufferedReader reader, int length) {
        char[] buffer = new char[length];
        try {
            reader.read(buffer);
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
