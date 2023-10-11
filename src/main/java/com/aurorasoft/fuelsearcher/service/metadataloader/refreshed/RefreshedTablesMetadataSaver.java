package com.aurorasoft.fuelsearcher.service.metadataloader.refreshed;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

import static com.aurorasoft.fuelsearcher.util.OutputStreamUtil.*;

@Component
public final class RefreshedTablesMetadataSaver {
    private final String preparedMetadataFilePath;

    public RefreshedTablesMetadataSaver(@Value("${metadata.file-path}") final String preparedMetadataFilePath) {
        this.preparedMetadataFilePath = preparedMetadataFilePath;
    }

    public void save(final List<TableMetadata> refreshedTablesMetadata) {
        try (final ObjectOutputStream outputStream = createObjectOutputStream(this.preparedMetadataFilePath)) {
            writeObjects(outputStream, refreshedTablesMetadata);
        } catch (final IOException cause) {
            throw new MetadataSavingException(cause);
        }
    }

    private static final class MetadataSavingException extends RuntimeException {

        @SuppressWarnings("unused")
        public MetadataSavingException() {

        }

        @SuppressWarnings("unused")
        public MetadataSavingException(final String description) {
            super(description);
        }

        public MetadataSavingException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public MetadataSavingException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
