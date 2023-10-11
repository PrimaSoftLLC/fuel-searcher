package com.aurorasoft.fuelsearcher.util;

import org.junit.Test;

import java.io.ObjectInputStream;

import static com.aurorasoft.fuelsearcher.util.InputStreamUtil.createObjectInputStream;
import static org.junit.Assert.assertNotNull;

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

}
