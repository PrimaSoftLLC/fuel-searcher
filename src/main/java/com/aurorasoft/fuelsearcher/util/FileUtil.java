package com.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.*;

@UtilityClass
public final class FileUtil {

    public static void writeFile(final MultipartFile file, final String filePath) {
        try {
            final Path path = Paths.get(filePath);
            write(path, file.getBytes());
        } catch (final IOException cause) {
            throw new FileException(cause);
        }
    }

    public static boolean isFileExist(final String filePath) {
        final Path path = Paths.get(filePath);
        return exists(path);
    }

    public static void removeFileIfExists(final String filePath) {
        try {
            final Path path = Paths.get(filePath);
            deleteIfExists(path);
        } catch (final IOException cause) {
            throw new FileException(cause);
        }
    }

    private static final class FileException extends RuntimeException {

        @SuppressWarnings("unused")
        public FileException() {

        }

        @SuppressWarnings("unused")
        public FileException(final String description) {
            super(description);
        }

        public FileException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public FileException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
