package by.aurorasoft.fuelsearcher.it.metadatacollecting.argumentsprovider.model;

import lombok.Builder;

@Builder
public record PropertyMetadataView(String propertyName, String[] allowableValues) {
}
