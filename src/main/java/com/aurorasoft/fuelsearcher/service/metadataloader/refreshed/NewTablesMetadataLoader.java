package com.aurorasoft.fuelsearcher.service.metadataloader.refreshed;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.RefreshedTablesMetadataFactory;
import com.aurorasoft.fuelsearcher.service.metadataloader.TablesMetadataLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(2)
@Component
@RequiredArgsConstructor
public final class NewTablesMetadataLoader implements TablesMetadataLoader {
    private final RefreshedTablesMetadataFactory refreshedMetadataFactory;
    private final NewTablesMetadataSaver newMetadataSaver;

    @Override
    public boolean isAbleToLoad() {
        return true;
    }

    @Override
    public List<TableMetadata> load() {
        final List<TableMetadata> tablesMetadata = this.refreshedMetadataFactory.create();
        this.newMetadataSaver.save(tablesMetadata);
        return tablesMetadata;
    }
}
