package com.aurorasoft.fuelsearcher.service.metadataloader.prepared;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.service.metadataloader.exception.TablesMetadataLoadingException;

import java.io.*;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.stream.Stream.generate;

public final class TableMetadataDeserializer implements AutoCloseable {
    private final ObjectInputStream objectInputStream;

    public TableMetadataDeserializer(final String filePath) {
        this.objectInputStream = createInputStream(filePath);
    }

    public List<TableMetadata> deserialize() {
        return generate(this::deserializeNext)
                .takeWhile(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    @Override
    public void close() {
        try {
            this.objectInputStream.close();
        } catch (final IOException cause) {
            throw new TablesMetadataLoadingException(cause);
        }
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

    private Optional<TableMetadata> deserializeNext() {
        try {
            final TableMetadata metadata = (TableMetadata) this.objectInputStream.readObject();
            return Optional.of(metadata);
        } catch (final EOFException endDeserializationException) {
            return empty();
        } catch (final IOException | ClassNotFoundException cause) {
            throw new TablesMetadataLoadingException(cause);
        }
    }
}
