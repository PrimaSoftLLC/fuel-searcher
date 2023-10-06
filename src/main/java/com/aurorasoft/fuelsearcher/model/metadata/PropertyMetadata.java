package com.aurorasoft.fuelsearcher.model.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@AllArgsConstructor
@Builder
public class PropertyMetadata implements Serializable {
    String propertyName;
    String[] allowableValues;
}
