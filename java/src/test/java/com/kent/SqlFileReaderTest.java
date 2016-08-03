package com.kent;

import com.kent.io.SqlFileReader;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class SqlFileReaderTest {

    @Test
    public void should_reader_all_files_contents() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        Path path1 = Paths.get(classLoader.getResource("V201608011725__first_sql_file.sql").getPath());
        Path path2 = Paths.get(classLoader.getResource("V201608011726__second_sql_file.sql").getPath());

        List<Path> files = Arrays.asList(path1, path2);
        String contents = SqlFileReader.readerFiles(files);

        String expectContents =
                "-- V201608011725__FIRST_SQL_FILE.SQL\n\n" +
                        "SELECT * FROM test;\n" +
                        "SELECT * FROM test2;\n\n" +
                        "-- V201608011726__SECOND_SQL_FILE.SQL\n\n" +
                        "SELECT * FROM test1;\n" +
                        "SELECT * FROM test2;\n";

        assertThat(contents, is(expectContents));
    }
}