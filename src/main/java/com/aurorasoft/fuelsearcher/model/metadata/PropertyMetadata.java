package com.aurorasoft.fuelsearcher.model.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class PropertyMetadata {
    String propertyName;
    String[] allowableValues;
}
