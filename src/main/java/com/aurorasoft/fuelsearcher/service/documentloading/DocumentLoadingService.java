package com.aurorasoft.fuelsearcher.service.documentloading;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;

import static java.nio.file.Files.readAllBytes;

@Service
public final class DocumentLoadingService {
    private final String fuelDocumentFilePath;
    private final String searcherConfigFilePath;

    public DocumentLoadingService(@Value("${fuel-document.path}") final String fuelDocumentFilePath,
                                  @Value("${fuel-searcher-config.path}") final String searcherConfigFilePath) {
        this.fuelDocumentFilePath = fuelDocumentFilePath;
        this.searcherConfigFilePath = searcherConfigFilePath;
    }

    public Resource loadFuelDocument() {
        return load(this.fuelDocumentFilePath);
    }

    public Resource loadSearcherConfigFile() {
        return load(this.searcherConfigFilePath);
    }

    private static Resource load(final String filePath) {
        try {
            final URI uri = URI.create(filePath);
            final Path path = Path.of(uri);
            final byte[] bytes = readAllBytes(path);
            return new ByteArrayResource(bytes);
        } catch (final IOException cause) {
            throw new DocumentLoadingException(cause);
        }
    }

    private static final class DocumentLoadingException extends RuntimeException {

        @SuppressWarnings("unused")
        public DocumentLoadingException() {

        }

        @SuppressWarnings("unused")
        public DocumentLoadingException(final String description) {
            super(description);
        }

        public DocumentLoadingException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public DocumentLoadingException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
