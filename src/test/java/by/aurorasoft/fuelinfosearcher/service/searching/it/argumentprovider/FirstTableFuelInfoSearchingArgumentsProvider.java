package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class FirstTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("FENDT 1050")
                                .machinery("Lemken Diamand 11")
                                .corpusCount("9")
                                .ploughingDepth("18–20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build(),
                        optionalFuelInfoFactory.apply(11.9, 21.7)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Кировец К-744 Р3")
                                .machinery("Kverneland RW 110")
                                .corpusCount("12")
                                .ploughingDepth("23–25")
                                .routingLength("Более 1000")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build(),
                        optionalFuelInfoFactory.apply(16.8, 21.3)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Кировец К-744 Р4")
                                .machinery("ППО-8-40")
                                .corpusCount("8")
                                .ploughingDepth("23–25")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 54...59 кПа")
                                .build(),
                        optionalFuelInfoFactory.apply(9., 21.5)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Кировец K744P2")
                                //TODO: do without nbsp symbol
                                .machinery("Vari Titan 10\u00A07+3 L100")
                                .corpusCount("10")
                                .ploughingDepth("23–25")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 54...59 кПа")
                                .build(),
                        optionalFuelInfoFactory.apply(10.1, 24.8)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("К744Р3")
                                .machinery("Kverneland RW 110")
                                .corpusCount("12")
                                .ploughingDepth("23–25")
                                .routingLength("201–300")
                                //TODO: сделать так, чтобы можно было не писать 'Плуга' и тут другое троеточие
                                .specificResistance("Удельное сопротивление плуга 60…65 кПа")
                                .build(),
                        optionalFuelInfoFactory.apply(11.3, 29.1)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Кировец K744P2")
                                //TODO: do without nbsp symbol
                                .machinery("Vari Titan 10\u00A07+3 L100")
                                .corpusCount("10")
                                .ploughingDepth("18–20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 54...59 кПа")
                                .build(),
                        optionalFuelInfoFactory.apply(10.9, 22.3)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("not exist tractor")
                                .machinery("Lemken Diamand 11")
                                .corpusCount("9")
                                .ploughingDepth("18–20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build(),
                        empty()
                ),
                //not existing plough mark
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("FENDT 1050")
                                .machinery("not existing plough mark")
                                .corpusCount("9")
                                .ploughingDepth("18–20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build(),
                        empty()
                ),
                //not existing corpus count
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("FENDT 1050")
                                .machinery("Lemken Diamand 11")
                                .corpusCount("-1")
                                .ploughingDepth("18–20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build(),
                        empty()
                ),
                //not existing ploughing depth
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("FENDT 1050")
                                .machinery("Lemken Diamand 11")
                                .corpusCount("9")
                                .ploughingDepth("1000000-2000000")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("FENDT 1050")
                                .machinery("Lemken Diamand 11")
                                .corpusCount("9")
                                .ploughingDepth("18–20")
                                .routingLength("1000000-2000000")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build(),
                        empty()
                ),
                //not existing specific resistance
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("К 744Р3")
                                .machinery("ППУ-13")
                                .corpusCount("13")
                                .ploughingDepth("25–27")
                                .routingLength("201–300")
                                .specificResistance("Удельное сопротивление плуга 100000000…200000000 кПа")
                                .build(),
                        empty()
                ),
                //'–' as fuel info
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("К 744Р3")
                                .machinery("ППУ-13")
                                .corpusCount("13")
                                .ploughingDepth("25–27")
                                .routingLength("201–300")
                                .specificResistance("Удельное сопротивление плуга 60…65 кПа")
                                .build(),
                        empty()
                )
        );
    }

}
