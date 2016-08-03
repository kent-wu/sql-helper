package com.kent.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class GlobUtils {
    public static Path getPath(String path) {
        String currentPath = System.getProperty("user.dir");
        String directory = path.substring(0, path.lastIndexOf("/"));

        return Paths.get(String.format("%s/%s", currentPath, directory));
    }

    public static String getGlob(String path) {
        String fileName = path.substring(path.lastIndexOf("/"));

        return "glob:**" + fileName;
    }
}
