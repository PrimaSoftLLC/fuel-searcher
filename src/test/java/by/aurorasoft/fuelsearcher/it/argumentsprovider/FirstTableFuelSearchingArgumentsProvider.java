package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.model.Fuel.createNotDefinedFuel;

public final class FirstTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ";

    public FirstTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("FENDT 1050")
                                .machinery("Lemken Diamand 11")
                                .corpusCount("9")
                                .ploughingDepth("18-20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build())
                        .expected(new Fuel(11.9, 21.7))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец К-744 Р3")
                                .machinery("Kverneland RW 110")
                                .corpusCount("12")
                                .ploughingDepth("23-25")
                                .routingLength("Более 1000")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build())
                        .expected(new Fuel(16.8, 21.3))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец К-744 Р4")
                                .machinery("ППО-8-40")
                                .corpusCount("8")
                                .ploughingDepth("23-25")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 54...59 кПа")
                                .build())
                        .expected(new Fuel(9., 21.5))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец K744P2")
                                .machinery("Vari Titan 10 7+3 L100")
                                .corpusCount("10")
                                .ploughingDepth("23-25")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 54...59 кПа")
                                .build())
                        .expected(new Fuel(10.1, 24.8))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("К744Р3")
                                .machinery("Kverneland RW 110")
                                .corpusCount("12")
                                .ploughingDepth("23-25")
                                .routingLength("201-300")
                                .specificResistance("Удельное сопротивление 60...65 кПа")
                                .build())
                        .expected(new Fuel(11.3, 29.1))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец K744P2")
                                .machinery("Vari Titan 10 7+3 L100")
                                .corpusCount("10")
                                .ploughingDepth("18-20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 54...59 кПа")
                                .build())
                        .expected(new Fuel(10.9, 22.3))
                        .build(),
                //'–' as fuel info
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("К 744Р3")
                                .machinery("ППУ-13")
                                .corpusCount("13")
                                .ploughingDepth("25-27")
                                .routingLength("201-300")
                                .specificResistance("Удельное сопротивление плуга 60...65 кПа")
                                .build())
                        .expected(createNotDefinedFuel())
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("Lemken Diamand 11")
                                .corpusCount("9")
                                .ploughingDepth("18-20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("FENDT 1050")
                                .machinery("not existing")
                                .corpusCount("9")
                                .ploughingDepth("18-20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build())
                        .build(),
                //not existing corpus count
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("FENDT 1050")
                                .machinery("Lemken Diamand 11")
                                .corpusCount("not existing")
                                .ploughingDepth("18-20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build())
                        .build(),
                //not existing ploughing depth
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("FENDT 1050")
                                .machinery("Lemken Diamand 11")
                                .corpusCount("9")
                                .ploughingDepth("not existing")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("FENDT 1050")
                                .machinery("Lemken Diamand 11")
                                .corpusCount("9")
                                .ploughingDepth("18-20")
                                .routingLength("not existing")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build())
                        .build(),
                //not existing specific resistance
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("К 744Р3")
                                .machinery("ППУ-13")
                                .corpusCount("13")
                                .ploughingDepth("25-27")
                                .routingLength("201-300")
                                .specificResistance("not existing")
                                .build())
                        .build()
        );
    }

}
