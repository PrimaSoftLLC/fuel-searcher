package com.aurorasoft.fuelsearcher.service.metadataloader.prepared;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.service.metadataloader.TablesMetadataLoader;
import com.aurorasoft.fuelsearcher.service.metadataloader.exception.TablesMetadataLoadingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@ConditionalOnProperty(prefix = "metadata", name = "refresh-on-start", havingValue = "false")
public final class PreparedTablesMetadataLoader implements TablesMetadataLoader {
    private final String filePath;

    public PreparedTablesMetadataLoader(@Value("${metadata.file-path}") final String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<TableMetadata> load() {
        try (final TableMetadataDeserializer metadataDeserializer = new TableMetadataDeserializer(this.filePath)) {
            return metadataDeserializer.deserialize();
        } catch (final IOException cause) {
            throw new TablesMetadataLoadingException(cause);
        }
    }
}
