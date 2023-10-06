package com.aurorasoft.fuelsearcher.service.tablesmetadataloader;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.service.tablesmetadataloader.exception.TablesMetadataLoadingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

@Component
@ConditionalOnProperty(prefix = "metadata-refreshing", name = "enable", havingValue = "false")
public final class PreparedTablesMetadataLoader implements TablesMetadataLoader {
    private final String filePath;

    public PreparedTablesMetadataLoader(@Value("${metadata-refreshing.file-path}") final String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<TableMetadata> load() {
        final List<TableMetadata> loadedTablesMetadata = new ArrayList<>();
        try (final ObjectInputStream objectInputStream = this.createInputStream()) {


            while (true) {
                final TableMetadata deserializedMetadata = (TableMetadata) objectInputStream.readObject();
                loadedTablesMetadata.add(deserializedMetadata);
            }
        } catch (final EOFException endDeserializationException) {
            return loadedTablesMetadata;
        } catch (final IOException | ClassNotFoundException exception) {
            throw new TablesMetadataLoadingException(exception);
        }
    }
}
