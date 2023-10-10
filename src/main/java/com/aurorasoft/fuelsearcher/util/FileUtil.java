package com.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.write;

@UtilityClass
public final class FileUtil {

    public static void writeFile(final MultipartFile file, final String filePath) {
        try {
            final Path path = Paths.get(filePath);
            write(path, file.getBytes());
        } catch (final IOException cause) {
            throw new FileWritingException(cause);
        }
    }

    private static final class FileWritingException extends RuntimeException {

        @SuppressWarnings("unused")
        public FileWritingException() {

        }

        @SuppressWarnings("unused")
        public FileWritingException(final String description) {
            super(description);
        }

        public FileWritingException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public FileWritingException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
