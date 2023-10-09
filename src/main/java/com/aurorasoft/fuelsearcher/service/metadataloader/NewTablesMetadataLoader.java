package com.aurorasoft.fuelsearcher.service.metadataloader;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.RefreshedTablesMetadataFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "metadata", name = "refresh-on-start", havingValue = "true")
public final class NewTablesMetadataLoader implements TablesMetadataLoader {
    private final RefreshedTablesMetadataFactory refreshedTablesMetadataFactory;

    @Override
    public List<TableMetadata> load() {
        return this.refreshedTablesMetadataFactory.create();
    }
}
