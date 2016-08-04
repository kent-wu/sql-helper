package com.kent;

import com.kent.io.SqlFileReader;
import com.kent.io.SqlFileWriter;
import com.kent.utils.GlobUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.kent.utils.Contants.FILE_SEPARATOR;

public class SqlHelper {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("-------------------------");
            System.out.println("usage:");
            System.out.println("java -jar sqlHelper.jar [sqlPath]");
            System.out.println("example:");
            System.out.println("java -jar sqlHelper.jar ../sql/*.sql");
            System.out.println("-------------------------");
            return;
        }

        String path = args[0];
        String targetFileName = "merge.sql";

        Finder finder = new Finder(GlobUtils.getGlob(path));

        Path currentPath = GlobUtils.getPath(path);
        String targetFile = currentPath + FILE_SEPARATOR + targetFileName;

        try {
            Files.walkFileTree(currentPath, finder);
            String contents = SqlFileReader.readerFiles(finder.getMatchFiles());
            SqlFileWriter.writeFile(Paths.get(targetFile), contents);

            System.out.println(String.format("Merge to %s successfully!", targetFile));
        } catch (IOException ex) {
            System.err.format("IOException: %s%n", ex);
        }
    }
}
