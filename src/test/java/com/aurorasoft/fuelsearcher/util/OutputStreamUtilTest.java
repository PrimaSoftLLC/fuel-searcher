package com.aurorasoft.fuelsearcher.util;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.aurorasoft.fuelsearcher.util.OutputStreamUtil.createObjectOutputStream;
import static java.nio.file.Files.createFile;
import static java.nio.file.Files.delete;

public final class OutputStreamUtilTest {
    private static final String NAME_TEMP_FILE = "temp.txt";
    private static final Path PATH_TEMP_FILE = Paths.get(NAME_TEMP_FILE);

    @BeforeClass
    public static void createTempFile()
            throws IOException {
        createFile(PATH_TEMP_FILE);
    }

    @AfterClass
    public static void deleteTempFile()
            throws IOException {
        delete(PATH_TEMP_FILE);
    }

    @Test
    public void objectOutputStreamShouldBeCreated() {
        final ObjectOutputStream actual = createObjectOutputStream(NAME_TEMP_FILE);
    }
}
