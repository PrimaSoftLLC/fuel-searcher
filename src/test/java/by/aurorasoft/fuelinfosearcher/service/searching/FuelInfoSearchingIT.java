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
                                .machinery("Lemken Diamand 11")
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
                                .machinery("Kverneland RW 110")
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
                                .machinery("ППО-8-40")
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
                                .machinery("Vari Titan 10\u00A07+3 L100")
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
                                .machinery("Kverneland RW 110")
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
                                .machinery("Vari Titan 10\u00A07+3 L100")
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
                ),

                //---------------FOR TABLE #2
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("CASE II Steiger 550")
                                //TODO: do without nbsp symbol
                                .machinery("Lemken EuroTitan 10\u00A08+3+1")
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
                                .machinery("Lemken EuroTitan 10")
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
                                .machinery("Lemken EuroTitan 10")
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
                                .machinery("Lemken EuroTitan 10")
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
                                .machinery("not exist")
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
                                .machinery("Lemken EuroTitan 10")
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
                                .machinery("Lemken EuroTitan 10")
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
                                .machinery("Lemken EuroTitan 10")
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
                                .machinery("Lemken EuroTitan 10")
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
                                .machinery("ПБН-6-50А")
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
                                .machinery("ПБН-6-50А")
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
                                .machinery("ПБН-6-50А")
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
                                .machinery("not exist")
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
                                .machinery("ПБН-6-50А")
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
                                .machinery("ПБН-6-50А")
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
                                .machinery("ПБН-6-50А")
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
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25–27")
                                .routingLength("Более 1000")
                                .soilType("Несуществующие почвы")
                                .build(),
                        empty()
                ),

                //FOR TABLE #4
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Clas Xerion 5000")
                                .machinery("Köckerling Allrounder 1200")
                                .workingWidth("12,0")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6…8 см")
                                .build(),
                        createFuelInfoWrappedByOptional(26.4, 14.1)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Clas Xerion 5000")
                                .machinery("Köckerling Allrounder 1200")
                                .workingWidth("12,0")
                                .routingLength("Более 1000")
                                .processingDepth("Глубина обработки 6…8 см")
                                .build(),
                        createFuelInfoWrappedByOptional(58.2, 7.5)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 3522")
                                .machinery("АПМ-8")
                                .workingWidth("8,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 6…8 см")
                                .build(),
                        createFuelInfoWrappedByOptional(24.0, 11.1)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 3022")
                                .machinery("КФУ-7,3")
                                .workingWidth("7,3")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6…8 см")
                                .build(),
                        createFuelInfoWrappedByOptional(17.3, 12.7)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4,0")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14…20 см")
                                .build(),
                        createFuelInfoWrappedByOptional(10.5, 8.7)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("not existing tractor")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4,0")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14…20 см")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 1522")
                                .machinery("not existing")
                                .workingWidth("4,0")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14…20 см")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("-1.0")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14…20 см")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4,0")
                                .routingLength("20-21")
                                .processingDepth("Глубина обработки 14…20 см")
                                .build(),
                        empty()
                ),
                //not existing processing depth
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4,0")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 66666666666…76666666666 см")
                                .build(),
                        empty()
                ),

                //For table #5
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Кировец К-744Р4")
                                .machinery("Доминанта Д-880")
                                .workingWidth("8,8")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6…8 см")
                                .build(),
                        createFuelInfoWrappedByOptional(23.7, 12.0)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 3022")
                                .machinery("АПД-7,5")
                                .workingWidth("7,5")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 6…8 см")
                                .build(),
                        createFuelInfoWrappedByOptional(20.4, 8.6)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6,0")
                                .workingWidth("6,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        createFuelInfoWrappedByOptional(16.2, 7.3)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("not existing")
                                .machinery("АКЧ-6,0")
                                .workingWidth("6,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 2022")
                                .machinery("not existing")
                                .workingWidth("6,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6,0")
                                .workingWidth("-1,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6,0")
                                .workingWidth("6,0")
                                .routingLength("20-21")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        empty()
                ),
                //not existing processing depth
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6,0")
                                .workingWidth("6,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 66666666666666666666…76666666666666666666 см")
                                .build(),
                        empty()
                ),

                //For table #6
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Кировец К-744Р4")
                                .machinery("Доминанта Д-880")
                                .workingWidth("8,8")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6…8 см")
                                .build(),
                        createFuelInfoWrappedByOptional(22.9, 12.5)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Кировец К-744Р4")
                                .machinery("АДС-6,0")
                                .workingWidth("6,0")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6…8 см")
                                .build(),
                        createFuelInfoWrappedByOptional(15.5, 12.8)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6,0")
                                .workingWidth("6,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        createFuelInfoWrappedByOptional(15.4, 7.7)
                ),

                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("not existing")
                                .machinery("АКЧ-6,0")
                                .workingWidth("6,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Беларус 2022")
                                .machinery("not existing")
                                .workingWidth("6,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6,0")
                                .workingWidth("-1,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6,0")
                                .workingWidth("6,0")
                                .routingLength("10–20")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        empty()
                ),
                //not existing processing depth
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6,0")
                                .workingWidth("6,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 666666666666666…766666666666666 см")
                                .build(),
                        empty()
                ),

                //For table #7
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА")
                                .tractor("Кировец К-744 Р3")
                                .machinery("Horsch Serto 12 SC")
                                .workingWidth("12,0")
                                .routingLength("Менее 150")
                                .sowingNorm("Норма высева 120–180 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(28.9, 8.2)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА")
                                .tractor("Беларус 3022")
                                .machinery("Horsch Pronto 6 DS")
                                .workingWidth("6,0")
                                .routingLength("150–200")
                                .sowingNorm("Норма высева 240–280 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(18.4, 9.3)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА")
                                .tractor("Беларус 1221")
                                .machinery("Horsch Sprinter 4 ST")
                                .workingWidth("4,0")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 240–280 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(15.9, 7.0)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА")
                                .tractor("not existing")
                                .machinery("Horsch Sprinter 4 ST")
                                .workingWidth("4,0")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 240–280 кг/га")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА")
                                .tractor("Беларус 1221")
                                .machinery("not existing")
                                .workingWidth("4,0")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 240–280 кг/га")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА")
                                .tractor("Беларус 1221")
                                .machinery("Horsch Sprinter 4 ST")
                                .workingWidth("-1,0")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 240–280 кг/га")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА")
                                .tractor("Беларус 1221")
                                .machinery("Horsch Sprinter 4 ST")
                                .workingWidth("4,0")
                                .routingLength("1000-1500")
                                .sowingNorm("Норма высева 240–280 кг/га")
                                .build(),
                        empty()
                ),
                //not existing sowing norm
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА")
                                .tractor("Беларус 1221")
                                .machinery("Horsch Sprinter 4 ST")
                                .workingWidth("4,0")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 66666666–76666666 кг/га")
                                .build(),
                        empty()
                ),

                //For table #8
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 3522")
                                .machinery("Horsch Maestro 24 S")
                                .workingWidth("16,8")
                                .routingLength("Менее 150")
                                .sowingNorm("Норма высева 15 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(32.9, 8.2)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("Ферабокс Футура Макси 8")
                                .workingWidth("5,6")
                                .routingLength("150–200")
                                .sowingNorm("Норма высева 30 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(13.6, 3.6)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("5,6")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(18.8, 3.1)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("not existing")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("5,6")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("not existing")
                                .workingWidth("5,6")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("-5,6")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("5,6")
                                .routingLength("1000-1500")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing sowing norm
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("5,6")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 1000000000 кг/га")
                                .build(),
                        empty()
                ),

                //For table #9
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("John Deere 8400")
                                .machinery("Tempo V-18")
                                .workingWidth("8,1")
                                .routingLength("Менее 150")
                                .sowingNorm("Норма высева семян 15 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(17.9, 3.45)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 1221")
                                .machinery("Meca V-4")
                                .workingWidth("5,4")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 30 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(15.6, 2.72)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 80/82")
                                .machinery("Tehnic NC")
                                .workingWidth("3,6")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(10.3, 2.64)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("not existing")
                                .machinery("Tehnic NC")
                                .workingWidth("3,6")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 80/82")
                                .machinery("not existing")
                                .workingWidth("3,6")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 80/82")
                                .machinery("Tehnic NC")
                                .workingWidth("-3,6")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 80/82")
                                .machinery("Tehnic NC")
                                .workingWidth("3,6")
                                .routingLength("1000-1500")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing sowing norm
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 80/82")
                                .machinery("Tehnic NC")
                                .workingWidth("3,6")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 99999999999999999 кг/га")
                                .build(),
                        empty()
                ),

                //For table #10
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("Беларус 3522")
                                .machinery("Амкодор «Veras» 12000")
                                .workingWidth("12,0")
                                .routingLength("Менее 150")
                                .sowingNorm("Норма высева семян 3 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(24.5, 9.3)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("Беларус 3522")
                                .machinery("Amazone Avant 6001-2")
                                .workingWidth("6,0")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(28.1, 7.9)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("Беларус 1221")
                                .machinery("Horsch Maestro DV")
                                .workingWidth("6,0")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build(),
                        createFuelInfoWrappedByOptional(21.3, 5.5)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("not existing")
                                .machinery("Horsch Maestro DV")
                                .workingWidth("6,0")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("Беларус 1221")
                                .machinery("not existing machinery")
                                .workingWidth("6,0")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build(),
                        empty()
                ),
                //nit existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("Беларус 1221")
                                .machinery("Horsch Maestro DV")
                                .workingWidth("-6,0")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("Беларус 1221")
                                .machinery("Horsch Maestro DV")
                                .workingWidth("6,0")
                                .routingLength("1500–2000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build(),
                        empty()
                ),
                //not existing sowing norm
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА")
                                .tractor("Беларус 1221")
                                .machinery("Horsch Maestro DV")
                                .workingWidth("6,0")
                                .routingLength("601–1000")
                                .sowingNorm("Норма высева семян 99999999999999 кг/га")
                                .build(),
                        empty()
                ),

                //For table #11
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("CASE IN Puma 210")
                                .machinery("AMAZONE ZG B 8200 SUPER")
                                .chargingMethodAndTransportDistance("Механизированный с загрузкой в конце гона")
                                .spreadRate("до 2,0")
                                .routingLength("Менее 150")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        createFuelInfoWrappedByOptional(71.2, 1.42)
                ),
                //TODO: doesn't work
//                Arguments.of(
//                        FuelInfoSpecification.builder()
//                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
//                                .tractor("CASE IN Puma 210")
//                                .machinery("Amazone ZA M 3000")
//                                .chargingMethodAndTransportDistance("Механизированный с подъездом 0,5…1,5")
//                                .spreadRate("6,1…8,0")
//                                .routingLength("Менее 150")
//                                .fertilizerType("Кристаллические удобрения")
//                                .build(),
//                        createFuelInfoWrappedByOptional(40.3, 1.95)
//                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 1523")
                                .machinery("УРМ 10")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 1,51…3,5")
                                .spreadRate("8,1…10,0")
                                .routingLength("Более 1000")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        createFuelInfoWrappedByOptional(76.6, 1.19)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 1523")
                                .machinery("REWO 8200")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 3,51…5,0")
                                .spreadRate("6,1…8,0")
                                .routingLength("150–200")
                                .fertilizerType("Пылевидные удобрения")
                                .build(),
                        createFuelInfoWrappedByOptional(37.0, 2.11)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 1523")
                                .machinery("SULKY XT 160")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 1,51…3,5")
                                .spreadRate("2,1…4,0")
                                .routingLength("150–200")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        createFuelInfoWrappedByOptional(73.8, 1.4)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 1221")
                                .machinery("RCW 10000")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 11,1…13,0")
                                .spreadRate("8,1…10,0")
                                .routingLength("Более 1000")
                                .fertilizerType("Пылевидные удобрения")
                                .build(),
                        createFuelInfoWrappedByOptional(29.3, 2.24)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 1221")
                                .machinery("Amazone ZA-M-1200")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 1,51…3,5")
                                .spreadRate("8,1…10,0")
                                .routingLength("150–200")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        createFuelInfoWrappedByOptional(27.4, 1.99)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("New Holland TD 5.110")
                                .machinery("Amazone ZA-M-1001 с надставкой бункера 500 л")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 5,1…7,0")
                                .spreadRate("до 2,0")
                                .routingLength("201–300")
                                .fertilizerType("Кристаллические удобрения")
                                .build(),
                        createFuelInfoWrappedByOptional(40.8, 1.25)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 82")
                                .machinery("Amazone ZA-M-900")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 11,1…13,0")
                                .spreadRate("8,1…10,0")
                                .routingLength("201–300")
                                .fertilizerType("Кристаллические удобрения")
                                .build(),
                        createFuelInfoWrappedByOptional(5.1, 9.05)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 82")
                                .machinery("Tornado 1300")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 5,1…7,0")
                                .spreadRate("6,1…8,0")
                                .routingLength("150–200")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        createFuelInfoWrappedByOptional(13.1, 3.91)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 82")
                                .machinery("SULKY XT 100 Polivrac")
                                .chargingMethodAndTransportDistance("Механизированный с загрузкой в конце гона")
                                .spreadRate("до 2,0")
                                .routingLength("201–300")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        createFuelInfoWrappedByOptional(75.5, 0.9)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ")
                                .tractor("Беларус 82")
                                .machinery("SULKY DX 20 c")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 3,51…5,0")
                                .spreadRate("6,1…8,0")
                                .routingLength("150–200")
                                .fertilizerType("Гранулированные удобрения")
                                .build(),
                        createFuelInfoWrappedByOptional(20., 2.85)
                )
        );
    }

    private static Optional<FuelInfo> createFuelInfoWrappedByOptional(final double generationNorm,
                                                                      final double consumption) {
        final FuelInfo fuelInfo = new FuelInfo(generationNorm, consumption);
        return Optional.of(fuelInfo);
    }
}
