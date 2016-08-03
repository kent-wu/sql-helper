package com.kent;

import com.kent.io.SqlFileReader;
import com.kent.io.SqlFileWriter;
import com.kent.utils.GlobUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SqlHelper {
    public static void main(String[] args) throws IOException {
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

        Path currentPath = GlobUtils.getPath(path);

        List<Path> sqlFiles = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            sqlFiles.add(Paths.get(currentPath + "/" + args[i]));
        }

        String contents = SqlFileReader.readerFiles(sqlFiles);

        String targetFile = currentPath + "/" + targetFileName;
        SqlFileWriter.writeFile(Paths.get(targetFile), contents);

        System.out.println(String.format("Merge to %s successfully!", targetFile));
    }
}
