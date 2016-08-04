package com.kent.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.kent.utils.Contants.FILE_SEPARATOR;
import static com.kent.utils.Contants.USER_DIRECTORY;

public class GlobUtils {

    public static Path getPath(String path) {
        String directory = path.substring(0, path.lastIndexOf(FILE_SEPARATOR));

        return Paths.get(String.format("%s/%s", USER_DIRECTORY, directory));
    }

    public static String getGlob(String path) {
        String fileName = path.substring(path.lastIndexOf(FILE_SEPARATOR));

        return "glob:**" + fileName;
    }
}
