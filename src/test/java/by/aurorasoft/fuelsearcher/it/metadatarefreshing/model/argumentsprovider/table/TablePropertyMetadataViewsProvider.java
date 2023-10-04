package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataView;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
public abstract class TablePropertyMetadataViewsProvider {
    private final String tableName;

    public final Stream<PropertyMetadataView> provide() {
        return this.createPropertyMetadataArguments(this::createPropertyMetadataArguments);
    }

    protected abstract Stream<PropertyMetadataView> createPropertyMetadataArguments(
            final PropertyMetadataArgumentsFactory factory
    );

    private PropertyMetadataView createPropertyMetadataArguments(final String propertyName,
                                                                 final String[] expectedPropertyAllowableValues) {
        return new PropertyMetadataView(this.tableName, propertyName, expectedPropertyAllowableValues);
    }

    @FunctionalInterface
    protected interface PropertyMetadataArgumentsFactory {
        PropertyMetadataView create(final String propertyName, final String[] expectedPropertyAllowableValues);
    }
}
