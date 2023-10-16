package com.aurorasoft.fuelsearcher.util;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedConstruction;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import static com.aurorasoft.fuelsearcher.util.OutputStreamUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public final class OutputStreamUtilTest {

    @Test
    public void objectInputStreamShouldBeCreated() {
        try (final MockedConstruction<FileOutputStream> mockedConstructionFileOutputStream = mockConstruction(FileOutputStream.class);
             final MockedConstruction<BufferedOutputStream> mockedConstructionBufferedOutputStream = mockConstruction(BufferedOutputStream.class);
             final MockedConstruction<ObjectOutputStream> mockedConstructionObjectOutputStream = mockConstruction(ObjectOutputStream.class)) {
            final String givenFilePath = "temp.txt";

            final ObjectOutputStream actual = createObjectOutputStream(givenFilePath);

            final List<FileOutputStream> constructedFileOutputStreams = mockedConstructionFileOutputStream.constructed();
            assertEquals(1, constructedFileOutputStreams.size());

            final List<BufferedOutputStream> constructedBufferedOutputStreams = mockedConstructionBufferedOutputStream
                    .constructed();
            assertEquals(1, constructedBufferedOutputStreams.size());

            final List<ObjectOutputStream> constructedObjectOutputStreams = mockedConstructionObjectOutputStream
                    .constructed();
            assertEquals(1, constructedObjectOutputStreams.size());
            final ObjectOutputStream expected = constructedObjectOutputStreams.get(0);
            assertSame(expected, actual);
        }
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

        final ArgumentCaptor<Object> objectArgumentCaptor = ArgumentCaptor.forClass(Object.class);
        verify(givenOutputStream, times(3)).writeObject(objectArgumentCaptor.capture());

        final List<Object> actualCapturedObjects = objectArgumentCaptor.getAllValues();
        Assertions.assertEquals(givenObjects, actualCapturedObjects);
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
