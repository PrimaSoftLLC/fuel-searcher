package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;

import java.util.stream.Stream;

public final class FirstTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<FuelArguments> createFuelArguments() {
        return Stream.of(
                FuelArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("FENDT 1050")
                                .machinery("Lemken Diamand 11")
                                .corpusCount("9")
                                .ploughingDepth("18-20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build())
                        .expected(new Fuel(11.9, 21.7))
                        .build(),
                FuelArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Кировец К-744 Р3")
                                .machinery("Kverneland RW 110")
                                .corpusCount("12")
                                .ploughingDepth("23-25")
                                .routingLength("Более 1000")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build())
                        .expected(new Fuel(16.8, 21.3))
                        .build(),
                FuelArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Кировец К-744 Р4")
                                .machinery("ППО-8-40")
                                .corpusCount("8")
                                .ploughingDepth("23-25")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 54...59 кПа")
                                .build())
                        .expected(new Fuel(9., 21.5))
                        .build(),
                FuelArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Кировец K744P2")
                                .machinery("Vari Titan 10 7+3 L100")
                                .corpusCount("10")
                                .ploughingDepth("23-25")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 54...59 кПа")
                                .build())
                        .expected(new Fuel(10.1, 24.8))
                        .build(),
                FuelArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("К744Р3")
                                .machinery("Kverneland RW 110")
                                .corpusCount("12")
                                .ploughingDepth("23-25")
                                .routingLength("201-300")
                                .specificResistance("Удельное сопротивление 60...65 кПа")
                                .build())
                        .expected(new Fuel(11.3, 29.1))
                        .build(),
                FuelArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
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
                FuelArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("К 744Р3")
                                .machinery("ППУ-13")
                                .corpusCount("13")
                                .ploughingDepth("25-27")
                                .routingLength("201-300")
                                .specificResistance("Удельное сопротивление плуга 60...65 кПа")
                                .build())
                        .build(),
                //not existing tractor
                FuelArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("not existing")
                                .machinery("Lemken Diamand 11")
                                .corpusCount("9")
                                .ploughingDepth("18-20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build())
                        .build(),
                //not existing machinery
                FuelArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("FENDT 1050")
                                .machinery("not existing")
                                .corpusCount("9")
                                .ploughingDepth("18-20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build())
                        .build(),
                //not existing corpus count
                FuelArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("FENDT 1050")
                                .machinery("Lemken Diamand 11")
                                .corpusCount("not existing")
                                .ploughingDepth("18-20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build())
                        .build(),
                //not existing ploughing depth
                FuelArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("FENDT 1050")
                                .machinery("Lemken Diamand 11")
                                .corpusCount("9")
                                .ploughingDepth("not existing")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build())
                        .build(),
                //not existing routing length
                FuelArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("FENDT 1050")
                                .machinery("Lemken Diamand 11")
                                .corpusCount("9")
                                .ploughingDepth("18-20")
                                .routingLength("not existing")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build())
                        .build(),
                //not existing specific resistance
                FuelArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
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
