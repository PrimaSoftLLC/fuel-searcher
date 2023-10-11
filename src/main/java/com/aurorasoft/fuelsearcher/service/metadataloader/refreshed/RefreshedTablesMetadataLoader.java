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
public final class RefreshedTablesMetadataLoader implements TablesMetadataLoader {
    private final RefreshedTablesMetadataFactory refreshedMetadataFactory;
    private final RefreshedTablesMetadataSaver refreshedMetadataSaver;

    @Override
    public boolean isAbleToLoad() {
        return true;
    }

    @Override
    public List<TableMetadata> load() {
        final List<TableMetadata> refreshedTablesMetadata = this.refreshedMetadataFactory.create();
        this.refreshedMetadataSaver.save(refreshedTablesMetadata);
        return refreshedTablesMetadata;
    }
}
