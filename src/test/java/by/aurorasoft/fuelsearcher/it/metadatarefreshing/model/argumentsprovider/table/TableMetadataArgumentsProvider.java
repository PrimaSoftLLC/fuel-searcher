package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataArguments;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

@RequiredArgsConstructor
public abstract class TableMetadataArgumentsProvider {
    private final String tableName;

    public final Stream<Arguments> provide() {
        return this.createPropertyMetadataArguments(this::createPropertyMetadataArguments).map(Arguments::of);
    }

    protected abstract Stream<PropertyMetadataArguments> createPropertyMetadataArguments(
            final PropertyMetadataArgumentsFactory factory
    );

    private PropertyMetadataArguments createPropertyMetadataArguments(final String propertyName,
                                                                      final String[] expectedPropertyAllowableValues) {
        return new PropertyMetadataArguments(this.tableName, propertyName, expectedPropertyAllowableValues);
    }

    @FunctionalInterface
    protected interface PropertyMetadataArgumentsFactory {
        PropertyMetadataArguments create(final String propertyName, final String[] expectedPropertyAllowableValues);
    }
}
