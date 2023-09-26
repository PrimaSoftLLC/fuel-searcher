package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notacceptable;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotAcceptableFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class ThirteenNotAcceptableTableFuelSearchingArgumentsProvider
        extends NotAcceptableTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ";

    public ThirteenNotAcceptableTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotAcceptableFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                NotAcceptableFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("БЕЛАРУС 3522")
                                .machinery("РОУМ-24")
                                .spreadRate("Менее 30")
                                .build())
                        .failedPropertyNames(Set.of("класс груза", "группа дорог", "расстояние транспортировки"))
                        .build()
        );
    }
}
