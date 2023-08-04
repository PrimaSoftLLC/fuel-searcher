package by.aurorasoft.fuelinfosearcher.service.searching;

import by.aurorasoft.fuelinfosearcher.base.AbstractContextTest;
import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.FuelInfoSearchingManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;


public final class FuelInfoSearchingIT extends AbstractContextTest {

    private final FuelInfoSearchingManager searchingManager = findBean(FuelInfoSearchingManager.class);

    @ParameterizedTest
    @MethodSource("fuelInfoSearchingArgumentProvider")
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public void fuelInfoShouldBeFound(final FuelInfoSpecification specification, final Optional<FuelInfo> expected) {
        final Optional<FuelInfo> actual = this.searchingManager.find(specification);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> fuelInfoSearchingArgumentProvider() {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("FENDT 1050")
                                .ploughMark("Lemken Diamand 11")
                                .corpusCount("9")
                                .ploughingDepth("18–20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build(),
                        createFuelInfoWrappedByOptional(11.9, 21.7)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Кировец К-744 Р3")
                                .ploughMark("Kverneland RW 110")
                                .corpusCount("12")
                                .ploughingDepth("23–25")
                                .routingLength("Более 1000")
                                .specificResistance("Удельное сопротивление 48...53 кПа")
                                .build(),
                        createFuelInfoWrappedByOptional(16.8, 21.3)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("К744Р3")
                                .ploughMark("Kverneland RW 110")
                                .corpusCount("12")
                                .ploughingDepth("23–25")
                                .routingLength("201–300")
                                //TODO: сделать так, чтобы можно было не писать 'Плуга' и тут другое троеточие
                                .specificResistance("Удельное сопротивление плуга 60…65 кПа")
                                .build(),
                        createFuelInfoWrappedByOptional(11.3, 29.1)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("not exist tractor")
                                .ploughMark("Lemken Diamand 11")
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
                                .ploughMark("not existing plough mark")
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
                                .ploughMark("Lemken Diamand 11")
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
                                .ploughMark("Lemken Diamand 11")
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
                                .ploughMark("Lemken Diamand 11")
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
                                .ploughMark("ППУ-13")
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
                                .ploughMark("ППУ-13")
                                .corpusCount("13")
                                .ploughingDepth("25–27")
                                .routingLength("201–300")
                                .specificResistance("Удельное сопротивление плуга 60…65 кПа")
                                .build(),
                        empty()
                )
        );
    }

    private static Optional<FuelInfo> createFuelInfoWrappedByOptional(final double generationNorm,
                                                                      final double consumption) {
        final FuelInfo fuelInfo = new FuelInfo(generationNorm, consumption);
        return Optional.of(fuelInfo);
    }
}
