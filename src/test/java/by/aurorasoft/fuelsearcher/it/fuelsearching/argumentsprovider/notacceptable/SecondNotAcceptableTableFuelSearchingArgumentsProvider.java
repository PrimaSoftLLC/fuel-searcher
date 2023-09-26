package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notacceptable;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotAcceptableFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class SecondNotAcceptableTableFuelSearchingArgumentsProvider extends NotAcceptableTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВСПАШКА СТЕРНИ";

    public SecondNotAcceptableTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotAcceptableFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                NotAcceptableFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("CASE II Steiger 550")
                                .machinery("Lemken EuroTitan 10 8+3+1")
                                .corpusCount("12")
                                .build())
                        .failedPropertyNames(Set.of("длина гона", "глубина вспашки", "удельное сопротивление"))
                        .build()
        );
    }
}
