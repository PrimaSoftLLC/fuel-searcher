package by.aurorasoft.fuelsearcher.it.metadatacollecting.model;

import lombok.Builder;

@Builder
public record PropertyMetadataView(String propertyName, String[] allowableValues) {
}
