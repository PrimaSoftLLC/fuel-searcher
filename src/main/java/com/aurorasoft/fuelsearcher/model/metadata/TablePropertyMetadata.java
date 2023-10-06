package com.aurorasoft.fuelsearcher.model.metadata;

import lombok.Value;

@Value
public class TablePropertyMetadata {
    String tableName;
    String propertyName;
    String[] allowableValues;
}
