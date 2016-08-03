package com.kent.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SqlFileReader {

    public static String readerFiles(List<Path> files) throws IOException {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        int index = 0;
        for (Path file : files) {
            String header = String.format("-- %s\n\n", file.getFileName().toString().toUpperCase());

            stringBuilder.append(header);

            try (BufferedReader reader = new BufferedReader(new FileReader(file.toString()))) {
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(ls);
                }
                if (index++ != (files.size() - 1)) {
                    stringBuilder.append(ls);
                }
            } catch (IOException ex) {
                System.err.format("IOException: %s%n", ex);
            }
        }

        return stringBuilder.toString();
    }
}