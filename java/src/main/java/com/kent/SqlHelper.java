package com.kent;

import com.kent.io.SqlFileReader;
import com.kent.io.SqlFileWriter;
import com.kent.utils.GlobUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SqlHelper {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("-------------------------");
            System.out.println("usage:");
            System.out.println("sqlHelper.jar [sqlPath] [targetFileName]\n");
            System.out.println("example:");
            System.out.println("sqlHelper.jar ../sql/*.sql merge.file");
            System.out.println("-------------------------");
            return;
        }

        String path = args[0];
        String targetFileName = args[1];

        Finder finder = new Finder(GlobUtils.getGlob(path));
        Path currentPath = GlobUtils.getPath(path);

        Files.walkFileTree(currentPath, finder);

        String contents = SqlFileReader.readerFiles(finder.getMatchFiles());

        String targetFile = currentPath + "/" + targetFileName;
        SqlFileWriter.writeFile(Paths.get(targetFile), contents);

        System.out.println(String.format("Merge to %s successfully!", targetFile));
    }
}
