package com.aurorasoft.fuelsearcher.util;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import static com.aurorasoft.fuelsearcher.util.InputStreamUtil.closeStream;
import static com.aurorasoft.fuelsearcher.util.InputStreamUtil.createObjectInputStream;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public final class InputStreamUtilTest {

    @Test
    public void objectInputStreamShouldBeCreated() {
        final String givenFilePath = "./src/test/resources/tables-metadata.txt";

        final ObjectInputStream actual = createObjectInputStream(givenFilePath);
        assertNotNull(actual);
    }

    @Test(expected = Exception.class)
    public void objectInputStreamShouldNotBeCreated() {
        final String givenFilePath = "./src/test/resources/not-exist.txt";

        createObjectInputStream(givenFilePath);
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

}
