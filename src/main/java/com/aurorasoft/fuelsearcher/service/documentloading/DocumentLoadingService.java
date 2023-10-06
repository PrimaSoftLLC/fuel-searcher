package com.aurorasoft.fuelsearcher.service.documentloading;

import com.aurorasoft.fuelsearcher.model.LoadedDocument;
import com.aurorasoft.fuelsearcher.model.LoadedDocument.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.aurorasoft.fuelsearcher.model.LoadedDocument.ContentType.DOCX;
import static com.aurorasoft.fuelsearcher.model.LoadedDocument.ContentType.XML;
import static java.nio.file.Files.readAllBytes;

@Service
public final class DocumentLoadingService {
    private static final String FUEL_DOCUMENT_NAME = "fuel-document";
    private static final ContentType FUEL_DOCUMENT_CONTENT_TYPE = DOCX;

    private static final String SEARCHER_CONFIG_FILE_NAME = "searcher-config";
    private static final ContentType SEARCHER_CONFIG_FILE_CONTENT_TYPE = XML;

    private final String fuelDocumentFilePath;
    private final String searcherConfigFilePath;

    public DocumentLoadingService(@Value("${fuel-document.path}") final String fuelDocumentFilePath,
                                  @Value("${fuel-searcher-config.path}") final String searcherConfigFilePath) {
        this.fuelDocumentFilePath = fuelDocumentFilePath;
        this.searcherConfigFilePath = searcherConfigFilePath;
    }

    public LoadedDocument loadFuelDocument() {
        return load(
                this.fuelDocumentFilePath,
                FUEL_DOCUMENT_NAME,
                FUEL_DOCUMENT_CONTENT_TYPE
        );
    }

    public LoadedDocument loadSearcherConfigFile() {
        return load(
                this.searcherConfigFilePath,
                SEARCHER_CONFIG_FILE_NAME,
                SEARCHER_CONFIG_FILE_CONTENT_TYPE
        );
    }

    private static LoadedDocument load(final String filePath, final String name, final ContentType contentType) {
        final byte[] bytes = loadAsBytes(filePath);
        return new LoadedDocument(bytes, name, contentType);
    }

    private static byte[] loadAsBytes(final String filePath) {
        try {
            final Path path = Paths.get(filePath);
            return readAllBytes(path);
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
