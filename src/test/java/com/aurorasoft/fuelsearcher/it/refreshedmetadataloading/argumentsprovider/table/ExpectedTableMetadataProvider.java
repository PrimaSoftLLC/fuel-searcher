package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;
import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public abstract class ExpectedTableMetadataProvider {
    private final String tableName;

    public final TableMetadata provide() {
        final Set<PropertyMetadata> propertiesMetadata = this.providePropertiesMetadata();
        return new TableMetadata(this.tableName, propertiesMetadata);
    }

    protected abstract Set<PropertyMetadata> providePropertiesMetadata();
}
