package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.success;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.SuccessFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class SecondSuccessTableFuelSearchingArgumentsProvider extends SuccessTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВСПАШКА СТЕРНИ";

    public SecondSuccessTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<SuccessFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("CASE II Steiger 550")
                                .machinery("Lemken EuroTitan 10 8+3+1")
                                .corpusCount("12")
                                .ploughingDepth("18-20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build())
                        .expected(new Fuel(13.8, 20.9))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец К-744 Р4")
                                .machinery("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21-22")
                                .routingLength("601-1000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build())
                        .expected(new Fuel(25.6, 15.0))
                        .build()
        );
    }
}
