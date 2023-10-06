package com.aurorasoft.fuelsearcher.service.tablesmetadataloader.prepared;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.service.tablesmetadataloader.exception.TablesMetadataLoadingException;

import java.io.*;
import java.util.Optional;

import static java.util.Optional.empty;

public final class TableMetadataDeserializer implements AutoCloseable {
    private final ObjectInputStream objectInputStream;

    public TableMetadataDeserializer(final String filePath) {
        this.objectInputStream = createInputStream(filePath);
    }

    public Optional<TableMetadata> deserializeNext() {
        try {
            final TableMetadata metadata = (TableMetadata) this.objectInputStream.readObject();
            return Optional.of(metadata);
        } catch (final EOFException endDeserializationException) {
            return empty();
        } catch (final IOException | ClassNotFoundException cause) {
            throw new TablesMetadataLoadingException(cause);
        }
    }

    @Override
    public void close()
            throws IOException {
        this.objectInputStream.close();
    }

    private static ObjectInputStream createInputStream(final String filePath) {
        try {
            final FileInputStream fileInputStream = new FileInputStream(filePath);
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            return new ObjectInputStream(bufferedInputStream);
        } catch (final IOException cause) {
            throw new TablesMetadataLoadingException(cause);
        }
    }
}
