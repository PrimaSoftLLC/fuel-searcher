package com.aurorasoft.fuelsearcher.service.documentuploading;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.write;

@Service
public final class FileUploadingService {
    private final String fuelDocumentPath;
    private final String searcherConfigFilePath;

    public FileUploadingService(@Value("${fuel-document.path}") final String fuelDocumentPath,
                                @Value("{fuel-searcher-config.path}") final String searcherConfigFilePath) {
        this.fuelDocumentPath = fuelDocumentPath;
        this.searcherConfigFilePath = searcherConfigFilePath;
    }

    public void upload(final MultipartFile fuelDocument, final MultipartFile searcherConfigFile) {
        rewriteFile(fuelDocument, this.fuelDocumentPath);
        rewriteFile(searcherConfigFile, this.searcherConfigFilePath);
    }

    private static void rewriteFile(final MultipartFile file, final String filePath) {
        try {
            final Path path = Paths.get(filePath);
            write(path, file.getBytes());
        } catch (final IOException cause) {
            throw new FileUploadingException(cause);
        }
    }

    private static final class FileUploadingException extends RuntimeException {

        @SuppressWarnings("unused")
        public FileUploadingException() {

        }

        @SuppressWarnings("unused")
        public FileUploadingException(final String description) {
            super(description);
        }

        public FileUploadingException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public FileUploadingException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
