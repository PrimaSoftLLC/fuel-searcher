package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.params.provider.Arguments;

import java.util.function.Supplier;
import java.util.stream.Stream;

@RequiredArgsConstructor
public abstract class TableFuelSearchingArgumentsProvider {
    private final String tableName;

    public final Stream<Arguments> provide() {
        return this.createFuelSearchingArguments().map(TableFuelSearchingArgumentsProvider::mapToArguments);
    }

    protected abstract Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    );

    private Stream<FuelSearchingArguments> createFuelSearchingArguments() {
        return this.createFuelSearchingArguments(this::createSpecificationBuilder);
    }

    private FuelSpecificationBuilder createSpecificationBuilder() {
        return FuelSpecification.builder().tableName(this.tableName);
    }

    private static Arguments mapToArguments(final FuelSearchingArguments fuelSearchingArguments) {
        return Arguments.of(
                fuelSearchingArguments.getSpecification(),
                fuelSearchingArguments.findExpected()
        );
    }
}
