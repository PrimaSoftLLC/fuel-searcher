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
            throw new SavingException(cause);
        }
    }

    private static final class SavingException extends RuntimeException {

        @SuppressWarnings("unused")
        public SavingException() {

        }

        @SuppressWarnings("unused")
        public SavingException(final String description) {
            super(description);
        }

        public SavingException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public SavingException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
