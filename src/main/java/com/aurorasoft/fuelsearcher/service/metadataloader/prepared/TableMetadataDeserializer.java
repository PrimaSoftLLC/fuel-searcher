package com.aurorasoft.fuelsearcher.service.metadataloader.prepared;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;

import java.io.ObjectInputStream;
import java.util.List;
import java.util.Optional;

import static com.aurorasoft.fuelsearcher.util.InputStreamUtil.*;
import static java.util.stream.Stream.generate;

public final class TableMetadataDeserializer implements AutoCloseable {
    private final ObjectInputStream inputStream;

    public TableMetadataDeserializer(final String filePath) {
        this.inputStream = createObjectInputStream(filePath);
    }

    public List<TableMetadata> deserialize() {
        return generate(this::deserializeNextIfExist)
                .takeWhile(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    @Override
    public void close() {
        closeStream(this.inputStream);
    }

    private Optional<TableMetadata> deserializeNextIfExist() {
        return readObjectIfExist(this.inputStream, TableMetadata.class);
    }
}
