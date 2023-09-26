package by.aurorasoft.fuelsearcher.it.metadatacollecting.argumentsprovider.model;

import lombok.Builder;

import java.util.Set;

@Builder
public record MetadataCollectingArguments(String tableName, Set<PropertyMetadataView> propertyMetadataViews) {
}
