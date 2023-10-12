package com.aurorasoft.fuelsearcher.util;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.ArgumentCaptor;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.aurorasoft.fuelsearcher.util.OutputStreamUtil.*;
import static java.io.File.pathSeparator;
import static java.nio.file.Files.createFile;
import static java.nio.file.Files.delete;
import static org.mockito.Mockito.*;

public final class OutputStreamUtilTest {
    private static final String NAME_TEMP_FILE = "temp.txt";

    @TempDir
    private static Path PARENT_PATH_TEMP_FILE;

    @BeforeClass
    public static void createTempFile()
            throws IOException {
        createFile(Paths.get(PARENT_PATH_TEMP_FILE.toString() + pathSeparator + NAME_TEMP_FILE));
    }

    @AfterClass
    public static void deleteTempFile()
            throws IOException {
        delete(Paths.get(PARENT_PATH_TEMP_FILE.toString() + pathSeparator + NAME_TEMP_FILE));
    }

    @org.junit.jupiter.api.Test
    public void objectOutputStreamShouldBeCreated() {
        final ObjectOutputStream actual = createObjectOutputStream(NAME_TEMP_FILE);
        Assertions.assertNotNull(actual);
    }

    @org.junit.jupiter.api.Test
    public void objectsShouldBeWritten()
            throws Exception {
        final ObjectOutputStream givenOutputStream = mock(ObjectOutputStream.class);

        final Object firstGivenObject = new Object();
        final Object secondGivenObject = new Object();
        final Object thirdGivenObject = new Object();
        final List<Object> givenObjects = List.of(firstGivenObject, secondGivenObject, thirdGivenObject);

        writeObjects(givenOutputStream, givenObjects);

        final ArgumentCaptor<Object> objectArgumentCaptor = ArgumentCaptor.forClass(Object.class);
        verify(givenOutputStream, times(3)).writeObject(objectArgumentCaptor.capture());

        final List<Object> actualCapturedObjects = objectArgumentCaptor.getAllValues();
        Assertions.assertEquals(givenObjects, actualCapturedObjects);
    }

    @org.junit.jupiter.api.Test
    public void objectsShouldNotBeWritten()
            throws Exception {
        final ObjectOutputStream givenOutputStream = mock(ObjectOutputStream.class);

        final Object firstGivenObject = new Object();
        final Object secondGivenObject = new Object();
        final Object thirdGivenObject = new Object();
        final List<Object> givenObjects = List.of(firstGivenObject, secondGivenObject, thirdGivenObject);

        doThrow(IOException.class).when(givenOutputStream).writeObject(same(secondGivenObject));

        writeObjects(givenOutputStream, givenObjects);
    }

    @org.junit.jupiter.api.Test
    public void objectShouldBeWritten()
            throws Exception {
        final ObjectOutputStream givenOutputStream = mock(ObjectOutputStream.class);
        final Object givenObject = new Object();

        writeObject(givenOutputStream, givenObject);

        verify(givenOutputStream, times(1)).writeObject(same(givenObject));
    }

    @org.junit.jupiter.api.Test
    public void objectShouldNotBeWritten()
            throws Exception {
        final ObjectOutputStream givenOutputStream = mock(ObjectOutputStream.class);
        final Object givenObject = new Object();

        doThrow(IOException.class).when(givenOutputStream).writeObject(same(givenObject));

        writeObject(givenOutputStream, givenObject);
    }
}
