package by.aurorasoft.fuelsearcher.it.argumentsprovider.notacceptable;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.NotAcceptableFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class SixthNotAcceptableTableFuelSearchingArgumentsProvider extends NotAcceptableTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ";

    public SixthNotAcceptableTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotAcceptableFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                NotAcceptableFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец К-744Р4")
                                .workingWidth("8.8")
                                .routingLength("Менее 150")
                                .build())
                        .failedPropertyNames(List.of("глубина обработки", "механизм"))
                        .build()
        );
    }
}
