package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notfound;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotFoundFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class SecondNotFoundTableFuelSearchingArgumentsProvider extends NotFoundTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВСПАШКА СТЕРНИ";

    public SecondNotFoundTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotFoundFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                //'–' as fuel
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец К-744 Р4")
                                .machinery("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21-22")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build())
                        .build(),
                //not existing tractor
                NotFoundFuelSearchingArguments.builder()
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
                NotFoundFuelSearchingArguments.builder()
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
                NotFoundFuelSearchingArguments.builder()
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
                NotFoundFuelSearchingArguments.builder()
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
                NotFoundFuelSearchingArguments.builder()
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
                NotFoundFuelSearchingArguments.builder()
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
