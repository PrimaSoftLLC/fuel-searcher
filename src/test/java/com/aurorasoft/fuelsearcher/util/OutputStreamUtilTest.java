package com.aurorasoft.fuelsearcher.util;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.aurorasoft.fuelsearcher.util.OutputStreamUtil.*;
import static java.nio.file.Files.createFile;
import static java.nio.file.Files.delete;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
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

    @Captor
    private ArgumentCaptor<Object> objectArgumentCaptor;

    @Test
    public void objectOutputStreamShouldBeCreated() {
        final ObjectOutputStream actual = createObjectOutputStream(NAME_TEMP_FILE);
        assertNotNull(actual);
    }

    @Test
    public void objectsShouldBeWritten()
            throws Exception {
        final ObjectOutputStream givenOutputStream = mock(ObjectOutputStream.class);

        final Object firstGivenObject = new Object();
        final Object secondGivenObject = new Object();
        final Object thirdGivenObject = new Object();
        final List<Object> givenObjects = List.of(firstGivenObject, secondGivenObject, thirdGivenObject);

        writeObjects(givenOutputStream, givenObjects);

        verify(givenOutputStream, times(3)).writeObject(this.objectArgumentCaptor.capture());

        final List<Object> actualCapturedObjects = this.objectArgumentCaptor.getAllValues();
        assertEquals(givenObjects, actualCapturedObjects);
    }

    @Test(expected = Exception.class)
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

    @Test
    public void objectShouldBeWritten()
            throws Exception {
        final ObjectOutputStream givenOutputStream = mock(ObjectOutputStream.class);
        final Object givenObject = new Object();

        writeObject(givenOutputStream, givenObject);

        verify(givenOutputStream, times(1)).writeObject(same(givenObject));
    }

    @Test(expected = Exception.class)
    public void objectShouldNotBeWritten()
            throws Exception {
        final ObjectOutputStream givenOutputStream = mock(ObjectOutputStream.class);
        final Object givenObject = new Object();

        doThrow(IOException.class).when(givenOutputStream).writeObject(same(givenObject));

        writeObject(givenOutputStream, givenObject);
    }
}
