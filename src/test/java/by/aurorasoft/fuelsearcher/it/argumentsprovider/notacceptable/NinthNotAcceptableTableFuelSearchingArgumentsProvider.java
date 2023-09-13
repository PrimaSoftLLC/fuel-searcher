package by.aurorasoft.fuelsearcher.it.argumentsprovider.notacceptable;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.NotAcceptableFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class NinthNotAcceptableTableFuelSearchingArgumentsProvider extends NotAcceptableTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ПОСЕВ САХАРНОЙ СВЕКЛЫ";

    public NinthNotAcceptableTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotAcceptableFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                NotAcceptableFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .workingWidth("8.1")
                                .routingLength("Менее 150")
                                .build())
                        .failedPropertyNames(new String[]{"трактор", "механизм", "норма высева"})
                        .build()
        );
    }
}
