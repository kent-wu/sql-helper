package com.kent;

import com.kent.utils.GlobUtils;
import org.junit.Test;

import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GlobUtilsTest {

    @Test
    public void should_get_directory_path() throws Exception {
        Path path = GlobUtils.getPath("../sql/*.sql");
        assertThat(path.toAbsolutePath().toString(),is("/Users/jhwu/develop/sql-helper/java/../sql"));
    }

    @Test
    public void should_get_glob() throws Exception {
        String glob = GlobUtils.getGlob("../sql/*.sql");
        assertThat(glob,is("glob:**/*.sql"));


    }
}