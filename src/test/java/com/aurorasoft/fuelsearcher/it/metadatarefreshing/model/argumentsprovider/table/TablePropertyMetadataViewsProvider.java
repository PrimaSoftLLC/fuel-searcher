package com.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataView;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
public abstract class TablePropertyMetadataViewsProvider {
    private final String tableName;

    public final Stream<PropertyMetadataView> provide() {
        return this.createViews(this::createView);
    }

    protected abstract Stream<PropertyMetadataView> createViews(final PropertyMetadataViewFactory factory);

    private PropertyMetadataView createView(final String propertyName, final String[] expectedPropertyAllowableValues) {
        return new PropertyMetadataView(this.tableName, propertyName, expectedPropertyAllowableValues);
    }

    @FunctionalInterface
    protected interface PropertyMetadataViewFactory {
        PropertyMetadataView create(final String propertyName, final String[] expectedPropertyAllowableValues);
    }
}
