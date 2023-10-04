package com.aurorasoft.fuelsearcher.it.metadatarefreshing.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class PropertyMetadataView {
    String tableName;
    String propertyName;
    String[] expectedPropertyAllowableValues;
}
