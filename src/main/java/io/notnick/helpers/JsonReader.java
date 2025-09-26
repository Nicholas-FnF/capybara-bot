package io.notnick.helpers;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.InputStream;
import java.util.Scanner;

public class JsonReader {
    private static final Logger LOGGER = Logger.getLogger(JsonReader.class.getName());

    public static String readResourceFile(String filename) {
        ClassLoader classLoader = JsonReader.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(filename)) {
            if (inputStream == null) {
                throw new IllegalAccessException("File not found: " + filename);
            }

            try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
                return scanner.useDelimiter("\\A").next();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Could not read file.");
            return null;
        }
    }
}
