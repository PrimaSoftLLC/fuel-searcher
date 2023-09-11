package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.lang.Double.NaN;

public final class SecondTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВСПАШКА СТЕРНИ";

    public SecondTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    ) {
        return Stream.of(
                FuelSearchingArguments.builder()
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
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец К-744 Р4")
                                .machinery("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21-22")
                                .routingLength("601-1000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build())
                        .expected(new Fuel(25.6, 15.0))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец К-744 Р4")
                                .machinery("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21-22")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build())
                        .expected(new Fuel(NaN, NaN))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21-22")
                                .routingLength("601-1000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец К-744 Р4")
                                .machinery("not existing")
                                .corpusCount("10")
                                .ploughingDepth("21-22")
                                .routingLength("601-1000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build())
                        .build(),
                //not existing corpus count
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец К-744 Р4")
                                .machinery("Lemken EuroTitan 10")
                                .corpusCount("not existing")
                                .ploughingDepth("21-22")
                                .routingLength("601-1000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build())
                        .build(),
                //not existing ploughing depth
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец К-744 Р4")
                                .machinery("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("not existing")
                                .routingLength("601-1000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец К-744 Р4")
                                .machinery("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21-22")
                                .routingLength("not existing")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build())
                        .build(),
                //not existing specific resistance
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец К-744 Р4")
                                .machinery("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21-22")
                                .routingLength("601-1000")
                                .specificResistance("not existing")
                                .build())
                        .build()
        );
    }
}
