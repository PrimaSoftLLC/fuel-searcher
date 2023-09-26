package by.aurorasoft.fuelsearcher.it.metadatacollecting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class PropertyMetadataView {
    String propertyName;
    String[] allowableValues;
}
