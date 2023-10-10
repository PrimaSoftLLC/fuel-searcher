package com.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.write;

@UtilityClass
public final class FileUtil {

    public static void rewriteFile(final MultipartFile file, final String filePath) {
        try {
            System.out.println(new String(file.getBytes()));
            final Path path = Paths.get(filePath);
            write(path, file.getBytes());
        } catch (final IOException cause) {
            throw new FileRewritingException(cause);
        }
    }

    private static final class FileRewritingException extends RuntimeException {

        @SuppressWarnings("unused")
        public FileRewritingException() {

        }

        @SuppressWarnings("unused")
        public FileRewritingException(final String description) {
            super(description);
        }

        public FileRewritingException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public FileRewritingException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
