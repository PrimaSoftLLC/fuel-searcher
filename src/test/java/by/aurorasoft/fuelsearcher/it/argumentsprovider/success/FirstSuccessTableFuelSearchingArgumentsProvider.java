package by.aurorasoft.fuelsearcher.it.argumentsprovider.success;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.SuccessFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class FirstSuccessTableFuelSearchingArgumentsProvider extends SuccessTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ";

    public FirstSuccessTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<SuccessFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                SuccessFuelSearchingArguments.builder()
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
                SuccessFuelSearchingArguments.builder()
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
                SuccessFuelSearchingArguments.builder()
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
                SuccessFuelSearchingArguments.builder()
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
                SuccessFuelSearchingArguments.builder()
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
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец K744P2")
                                .machinery("Vari Titan 10 7+3 L100")
                                .corpusCount("10")
                                .ploughingDepth("18-20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 54...59 кПа")
                                .build())
                        .expected(new Fuel(10.9, 22.3))
                        .build()
        );
    }
}
