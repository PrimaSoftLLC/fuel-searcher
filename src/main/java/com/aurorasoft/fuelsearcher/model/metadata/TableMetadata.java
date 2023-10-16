package com.aurorasoft.fuelsearcher.model.metadata;

import com.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

@Value
@AllArgsConstructor
@Builder
public class TableMetadata implements Translatable, Serializable {
    String tableName;
    Set<PropertyMetadata> propertiesMetadata;

    @Override
    public String findAlias() {
        return this.tableName;
    }
}
