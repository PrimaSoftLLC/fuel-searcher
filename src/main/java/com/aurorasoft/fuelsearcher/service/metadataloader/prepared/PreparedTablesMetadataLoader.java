package com.aurorasoft.fuelsearcher.service.metadataloader.prepared;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.service.metadataloader.TablesMetadataLoader;
import com.aurorasoft.fuelsearcher.service.metadataloader.exception.TablesMetadataLoadingException;
import com.aurorasoft.fuelsearcher.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static com.aurorasoft.fuelsearcher.util.FileUtil.isFileExist;

@Order(1)
@Component
public final class PreparedTablesMetadataLoader implements TablesMetadataLoader {
    private final String filePath;

    public PreparedTablesMetadataLoader(@Value("${metadata.file-path}") final String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean isAbleToLoad() {
        return isFileExist(this.filePath);
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
