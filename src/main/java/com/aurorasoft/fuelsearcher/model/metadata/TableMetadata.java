package com.aurorasoft.fuelsearcher.model.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder
public class TableMetadata {
    String tableName;
    List<PropertyMetadata> propertiesMetadata;
}
