package com.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

@UtilityClass
public final class OutputStreamUtil {

    public static ObjectOutputStream createObjectOutputStream(final String filePath) {
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            return new ObjectOutputStream(bufferedOutputStream);
        } catch (final IOException cause) {
            throw new OutputStreamException(cause);
        }
    }

    public static void writeObjects(final ObjectOutputStream outputStream, final List<?> objects) {
        objects.forEach(object -> writeObject(outputStream, object));
    }

    public static void writeObject(final ObjectOutputStream outputStream, final Object object) {
        try {
            outputStream.writeObject(object);
        } catch (final IOException cause) {
            throw new OutputStreamException(cause);
        }
    }

    private static final class OutputStreamException extends RuntimeException {

        public OutputStreamException() {

        }

        public OutputStreamException(final String description) {
            super(description);
        }

        public OutputStreamException(final Exception cause) {
            super(cause);
        }

        public OutputStreamException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
