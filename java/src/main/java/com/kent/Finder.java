package com.kent;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Finder extends SimpleFileVisitor<Path> {
    private List<Path> matchFiles = new ArrayList<Path>();
    final PathMatcher pathMatcher;

    public Finder(String glob) {
        pathMatcher = FileSystems.getDefault().getPathMatcher(glob);
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        isMatch(path);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return FileVisitResult.CONTINUE;
    }

    private void isMatch(Path path) {
        if (pathMatcher.matches(path)) {
            matchFiles.add(path);
        }
    }

    public List<Path> getMatchFiles() {
        return matchFiles;
    }
}
