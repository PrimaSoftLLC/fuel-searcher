package com.aurorasoft.fuelsearcher.service.metadataloader.prepared;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.service.metadataloader.TablesMetadataLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.aurorasoft.fuelsearcher.util.FileUtil.isFileExist;

@Order(1)
@Component
public final class PreparedTablesMetadataLoader implements TablesMetadataLoader {
    private final String preparedMetadataFilePath;

    public PreparedTablesMetadataLoader(@Value("${metadata.file-path}") final String preparedMetadataFilePath) {
        this.preparedMetadataFilePath = preparedMetadataFilePath;
    }

    @Override
    public boolean isAbleToLoad() {
        return isFileExist(this.preparedMetadataFilePath);
    }

    @Override
    public List<TableMetadata> load() {
        try (final TableMetadataDeserializer metadataDeserializer = new TableMetadataDeserializer(this.preparedMetadataFilePath)) {
            return metadataDeserializer.deserialize();
        }
    }
}
