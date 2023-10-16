package com.aurorasoft.fuelsearcher.util;

import org.junit.Test;
import org.mockito.MockedConstruction;

import java.io.*;
import java.util.List;
import java.util.Optional;

import static com.aurorasoft.fuelsearcher.util.InputStreamUtil.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public final class InputStreamUtilTest {

    @Test
    public void objectInputStreamShouldBeCreated() {
        try (final MockedConstruction<FileInputStream> mockedConstructionFileInputStream = mockConstruction(FileInputStream.class);
             final MockedConstruction<BufferedInputStream> mockedConstructionBufferedInputStream = mockConstruction(BufferedInputStream.class);
             final MockedConstruction<ObjectInputStream> mockedConstructionObjectInputStream = mockConstruction(ObjectInputStream.class)) {
            final String givenFilePath = "temp.txt";

            final ObjectInputStream actual = createObjectInputStream(givenFilePath);

            final List<FileInputStream> constructedFileInputStreams = mockedConstructionFileInputStream.constructed();
            assertEquals(1, constructedFileInputStreams.size());

            final List<BufferedInputStream> constructedBufferedInputStreams = mockedConstructionBufferedInputStream
                    .constructed();
            assertEquals(1, constructedBufferedInputStreams.size());

            final List<ObjectInputStream> constructedObjectInputStreams = mockedConstructionObjectInputStream
                    .constructed();
            assertEquals(1, constructedObjectInputStreams.size());
            final ObjectInputStream expected = constructedObjectInputStreams.get(0);
            assertSame(expected, actual);
        }
    }

    @Test
    public void streamShouldBeClosed()
            throws Exception {
        final InputStream givenInputStream = mock(InputStream.class);

        closeStream(givenInputStream);

        verify(givenInputStream, times(1)).close();
    }

    @Test(expected = Exception.class)
    public void streamShouldNotBeClosed()
            throws Exception {
        final InputStream givenInputStream = mock(InputStream.class);

        doThrow(IOException.class).when(givenInputStream).close();

        closeStream(givenInputStream);
    }

    @Test
    public void objectShouldBeRead()
            throws Exception {
        final ObjectInputStream givenInputStream = mock(ObjectInputStream.class);

        final TestObject givenObject = new TestObject();
        when(givenInputStream.readObject()).thenReturn(givenObject);

        final Optional<TestObject> optionalActual = readObjectIfExist(givenInputStream, TestObject.class);
        assertTrue(optionalActual.isPresent());
        final TestObject actual = optionalActual.get();
        assertSame(givenObject, actual);
    }

    @Test(expected = ClassCastException.class)
    public void objectShouldNotBeReadBecauseOfClassCastException()
            throws Exception {
        final ObjectInputStream givenInputStream = mock(ObjectInputStream.class);

        final TestObject givenObject = new TestObject();
        when(givenInputStream.readObject()).thenReturn(givenObject);

        readObjectIfExist(givenInputStream, Integer.class);
    }

    @Test
    public void objectShouldNotBeReadBecauseOfEndDeserializationException()
            throws Exception {
        final ObjectInputStream givenInputStream = mock(ObjectInputStream.class);

        doThrow(EOFException.class).when(givenInputStream).readObject();

        final Optional<TestObject> optionalActual = readObjectIfExist(givenInputStream, TestObject.class);
        assertTrue(optionalActual.isEmpty());
    }

    @Test(expected = Exception.class)
    public void objectShouldNotBeReadBecauseOfIOException()
            throws Exception {
        final ObjectInputStream givenInputStream = mock(ObjectInputStream.class);

        doThrow(IOException.class).when(givenInputStream).readObject();

        readObjectIfExist(givenInputStream, TestObject.class);
    }

    @Test(expected = Exception.class)
    public void objectShouldNotBeReadBecauseOfClassNotFoundException()
            throws Exception {
        final ObjectInputStream givenInputStream = mock(ObjectInputStream.class);

        doThrow(ClassNotFoundException.class).when(givenInputStream).readObject();

        readObjectIfExist(givenInputStream, TestObject.class);
    }

    private static final class TestObject {

    }
}
