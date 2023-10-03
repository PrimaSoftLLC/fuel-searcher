package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataArguments;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.params.provider.Arguments;

import java.util.function.BiFunction;
import java.util.stream.Stream;

@RequiredArgsConstructor
public abstract class TableMetadataArgumentsProvider {
    private final String tableName;

    public final Stream<Arguments> provide() {
        return this.createPropertyMetadataArguments().map(Arguments::of);
    }

    protected abstract Stream<PropertyMetadataArguments> createPropertyMetadataArguments(
            final PropertyMetadataArgumentsFactory factory
    );

    private Stream<PropertyMetadataArguments> createPropertyMetadataArguments() {
        final PropertyMetadataArgumentsFactory factory = (propertyName, expectedPropertyAllowableValues)
                -> new PropertyMetadataArguments(this.tableName, propertyName, expectedPropertyAllowableValues);
        return this.createPropertyMetadataArguments(factory);
    }

    @FunctionalInterface
    protected interface PropertyMetadataArgumentsFactory extends BiFunction<String, String[], PropertyMetadataArguments> {

    }
}
