package com.kent.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;

import static com.kent.utils.Contants.LINE_SEPARATOR;

public class SqlFileReader {

    public static String readerFiles(List<Path> files) throws IOException {
        String line;
        StringBuilder stringBuilder = new StringBuilder();

        int index = 0;
        for (Path file : files) {
            String header = String.format("-- %s\n\n", file.getFileName().toString().toUpperCase());

            stringBuilder.append(header);

            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file.toFile()), "UTF-8");

            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(LINE_SEPARATOR);
                }
                if (index++ != (files.size() - 1)) {
                    stringBuilder.append(LINE_SEPARATOR);
                }
            }
        }

        return stringBuilder.toString();
    }
}