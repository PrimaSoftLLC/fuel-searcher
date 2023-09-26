package by.aurorasoft.fuelsearcher.it.metadatacollecting.argumentsprovider;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class MetadataCollectingArgumentsProvider {
    private final String tableName;

    protected abstract Stream<T> createArguments(final Supplier<FuelSpecification.FuelSpecificationBuilder> specificationBuilderSupplier);
}
