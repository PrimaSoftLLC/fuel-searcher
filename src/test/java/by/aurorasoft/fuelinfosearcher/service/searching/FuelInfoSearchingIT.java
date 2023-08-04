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
                //---------------FOR TABLE #1
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
                                .tractor("Кировец К-744 Р4")
                                .ploughMark("ППО-8-40")
                                .corpusCount("8")
                                .ploughingDepth("23–25")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 54...59 кПа")
                                .build(),
                        createFuelInfoWrappedByOptional(9., 21.5)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Кировец K744P2")
                                //TODO: do without nbsp symbol
                                .ploughMark("Vari Titan 10\u00A07+3 L100")
                                .corpusCount("10")
                                .ploughingDepth("23–25")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 54...59 кПа")
                                .build(),
                        createFuelInfoWrappedByOptional(10.1, 24.8)
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
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Кировец K744P2")
                                //TODO: do without nbsp symbol
                                .ploughMark("Vari Titan 10\u00A07+3 L100")
                                .corpusCount("10")
                                .ploughingDepth("18–20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 54...59 кПа")
                                .build(),
                        createFuelInfoWrappedByOptional(10.9, 22.3)
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
                ),

                //---------------FOR TABLE #2
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("CASE II Steiger 550")
                                //TODO: do without nbsp symbol
                                .ploughMark("Lemken EuroTitan 10\u00A08+3+1")
                                .corpusCount("12")
                                .ploughingDepth("18–20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build(),
                        createFuelInfoWrappedByOptional(13.8, 20.9)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("Кировец К-744 Р4")
                                .ploughMark("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21–22")
                                .routingLength("601–1000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build(),
                        createFuelInfoWrappedByOptional(25.6, 15.0)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("Кировец К-744 Р4")
                                .ploughMark("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21–22")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build(),
                        empty()
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("not exist")
                                .ploughMark("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21–22")
                                .routingLength("601–1000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build(),
                        empty()
                ),
                //not exist plough mark
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("Кировец К-744 Р4")
                                .ploughMark("not exist")
                                .corpusCount("10")
                                .ploughingDepth("21–22")
                                .routingLength("601–1000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build(),
                        empty()
                ),
                //not exist corpus count
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("Кировец К-744 Р4")
                                .ploughMark("Lemken EuroTitan 10")
                                .corpusCount("-1")
                                .ploughingDepth("21–22")
                                .routingLength("601–1000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build(),
                        empty()
                ),
                //not exist ploughing depth
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("Кировец К-744 Р4")
                                .ploughMark("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("100000000-200000000")
                                .routingLength("601–1000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build(),
                        empty()
                ),
                //not exist routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("Кировец К-744 Р4")
                                .ploughMark("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21–22")
                                .routingLength("10000000-20000000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build(),
                        empty()
                ),
                //not exist specific resistance
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("Кировец К-744 Р4")
                                .ploughMark("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21–22")
                                .routingLength("601–1000")
                                .specificResistance("Удельное сопротивление -5...-1 кПа")
                                .build(),
                        empty()
                ),

                //for table #3
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3522")
                                .ploughMark("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("20–22")
                                .routingLength("Менее 150")
                                .soilType("Минеральные почвы")
                                .build(),
                        createFuelInfoWrappedByOptional(8.5, 23.7)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .ploughMark("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25–27")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build(),
                        createFuelInfoWrappedByOptional(11.4, 20.5)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("not exist")
                                .ploughMark("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25–27")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build(),
                        empty()
                ),
                //not existing plough mark
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .ploughMark("not exist")
                                .corpusCount("6")
                                .ploughingDepth("25–27")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build(),
                        empty()
                ),
                //not existing corpus count
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .ploughMark("ПБН-6-50А")
                                .corpusCount("-1")
                                .ploughingDepth("25–27")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build(),
                        empty()
                ),
                //not existing ploughing depth
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .ploughMark("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("1500-2000")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .ploughMark("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25–27")
                                .routingLength("5000-5001")
                                .soilType("Торфяные почвы")
                                .build(),
                        empty()
                ),
                //not existing soil type
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .ploughMark("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25–27")
                                .routingLength("Более 1000")
                                .soilType("Несуществующие почвы")
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
