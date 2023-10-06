package com.aurorasoft.fuelsearcher.model.metadata;

import com.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder
public class TableMetadata implements Translatable {
    String tableName;
    List<PropertyMetadata> propertiesMetadata;

    @Override
    public String findAlias() {
        return this.tableName;
    }
}
