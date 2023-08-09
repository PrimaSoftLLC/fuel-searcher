package by.aurorasoft.fuelinfosearcher.configuration;

import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import by.aurorasoft.fuelinfosearcher.service.documentloading.FuelDocumentLoadingService;
import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Configuration
public class FuelInfoConfiguration {
    private static final String NAME_FIRST_TABLE = "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ";
    private static final String NAME_SECOND_TABLE = "ВСПАШКА СТЕРНИ";
    private static final String NAME_THIRD_TABLE = "ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ";
    private static final String NAME_FOURTH_TABLE = "СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ";
    private static final String NAME_FIFTH_TABLE = "ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ";
    private static final String NAME_SIXTH_TABLE = "ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ";
    private static final String NAME_SEVENTH_TABLE = "ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА";
    private static final String NAME_EIGHTH_TABLE = "ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА";
    private static final String NAME_NINTH_TABLE = "ПОСЕВ САХАРНОЙ СВЕКЛЫ";
    private static final String NAME_TENTH_TABLE = "ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА";
    private static final String NAME_ELEVENTH_TABLE = "ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ";
    private static final String NAME_TWELFTH_TABLE = "ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ";
    private static final String NAME_FIFTEENTH_TABLE = "КОШЕНИЕ СЕЯНЫХ И ЕСТЕСТВЕННЫХ ТРАВ";

    @Bean
    public FuelDocument document(final FuelDocumentLoadingService loadingService) {
        return loadingService.load();
    }

    @Bean
    public FuelInfoOffsetFromRoutingLengthStorage fuelInfoOffsetFromRoutingLengthStorage() {
        return new FuelInfoOffsetFromRoutingLengthStorage(
                ofEntries(
                        entry(NAME_FIRST_TABLE, createFirstTableFuelInfoOffsetsByRoutingLengths()),
                        entry(NAME_SECOND_TABLE, createSecondTableFuelInfoOffsetsByRoutingLengths()),
                        entry(NAME_THIRD_TABLE, createThirdTableFuelInfoOffsetsByRoutingLengths()),
                        entry(NAME_FOURTH_TABLE, createFourthTableFuelInfoOffsetsByRoutingLengths()),
                        entry(NAME_FIFTH_TABLE, createFifthTableFuelInfoOffsetsByRoutingLengths()),
                        entry(NAME_SIXTH_TABLE, createSixthTableFuelInfoOffsetsByRoutingLengths()),
                        entry(NAME_SEVENTH_TABLE, createSeventhTableFuelInfoOffsetsByRoutingLengths()),
                        entry(NAME_EIGHTH_TABLE, createEighthTableFuelInfoOffsetsByRoutingLengths()),
                        entry(NAME_NINTH_TABLE, createNinthTableFuelInfoOffsetsByRoutingLengths()),
                        entry(NAME_TENTH_TABLE, createTenthTableFuelInfoOffsetsByRoutingLengths()),
                        entry(NAME_ELEVENTH_TABLE, createEleventhTableFuelInfoOffsetsByRoutingLengths()),
                        entry(NAME_TWELFTH_TABLE, createTwelfthTableFuelInfoOffsetsByRoutingLengths()),
                        //TODO 13, 14
                        entry(NAME_FIFTEENTH_TABLE, createFifteenthTableFuelInfoOffsetsByRoutingLengths())
                )
        );
    }

    private static Map<String, Integer> createFirstTableFuelInfoOffsetsByRoutingLengths() {
        return Map.of(
                "Менее 150", 2,
                "150–200", 3,
                "201–300", 4,
                "301–400", 5,
                "401–600", 6,
                "601–1000", 7,
                "Более 1000", 8
        );
    }

    //'Состав аггрегата' по-другому стоит - не как в первой таблице => смещения другие
    private static Map<String, Integer> createSecondTableFuelInfoOffsetsByRoutingLengths() {
        return Map.of(
                "Менее 150", 0,
                "150–200", 1,
                "201–300", 2,
                "301–400", 3,
                "401–600", 4,
                "601–1000", 5,
                "Более 1000", 6
        );
    }

    private static Map<String, Integer> createThirdTableFuelInfoOffsetsByRoutingLengths() {
        return Map.of(
                "Менее 150", 2,
                "150–200", 3,
                "201–300", 4,
                "301–400", 5,
                "401–600", 6,
                "601–1000", 7,
                "Более 1000", 8
        );
    }

    //'Состав аггрегата' по-другому стоит - не как в первой таблице => смещения другие
    private static Map<String, Integer> createFourthTableFuelInfoOffsetsByRoutingLengths() {
        return Map.of(
                "Менее 150", 0,
                "150–200", 1,
                "201–300", 2,
                "301–400", 3,
                "401–600", 4,
                "601–1000", 5,
                "Более 1000", 6
        );
    }

    //'Состав аггрегата' по-другому стоит - не как в первой таблице => смещения другие
    private static Map<String, Integer> createFifthTableFuelInfoOffsetsByRoutingLengths() {
        return Map.of(
                "Менее 150", 0,
                "150–200", 1,
                "201–300", 2,
                "301–400", 3,
                "401–600", 4,
                "601–1000", 5,
                "Более 1000", 6
        );
    }

    //'Состав аггрегата' по-другому стоит - не как в первой таблице => смещения другие
    private static Map<String, Integer> createSixthTableFuelInfoOffsetsByRoutingLengths() {
        return Map.of(
                "Менее 150", 0,
                "150–200", 1,
                "201–300", 2,
                "301–400", 3,
                "401–600", 4,
                "601–1000", 5,
                "Более 1000", 6
        );
    }

    private static Map<String, Integer> createSeventhTableFuelInfoOffsetsByRoutingLengths() {
        return Map.of(
                "Менее 150", 0,
                "150–200", 1,
                "201–300", 2,
                "301–400", 3,
                "401–600", 4,
                "601–1000", 5,
                "Более 1000", 6
        );
    }

    private static Map<String, Integer> createEighthTableFuelInfoOffsetsByRoutingLengths() {
        return Map.of(
                "Менее 150", 0,
                "150–200", 1,
                "201–300", 2,
                "301–400", 3,
                "401–600", 4,
                "601–1000", 5,
                "Более 1000", 6
        );
    }

    private static Map<String, Integer> createNinthTableFuelInfoOffsetsByRoutingLengths() {
        return Map.of(
                "Менее 150", 0,
                "150–200", 1,
                "201–300", 2,
                "301–400", 3,
                "401–600", 4,
                "601–1000", 5,
                "Более 1000", 6
        );
    }

    private static Map<String, Integer> createTenthTableFuelInfoOffsetsByRoutingLengths() {
        return Map.of(
                "Менее 150", 0,
                "150–200", 1,
                "201–300", 2,
                "301–400", 3,
                "401–600", 4,
                "601–1000", 5,
                "Более 1000", 6
        );
    }

    private static Map<String, Integer> createEleventhTableFuelInfoOffsetsByRoutingLengths() {
        return Map.of(
                "Менее 150", 0,
                "150–200", 1,
                "201–300", 2,
                "301–400", 3,
                "401–600", 4,
                "601–1000", 5,
                "Более 1000", 6
        );
    }

    private static Map<String, Integer> createTwelfthTableFuelInfoOffsetsByRoutingLengths() {
        return Map.of(
                "Менее 150", 0,
                "150–200", 1,
                "201–300", 2,
                "301–400", 3,
                "401–600", 4,
                "601–1000", 5,
                "Более 1000", 6
        );
    }

    private static Map<String, Integer> createFifteenthTableFuelInfoOffsetsByRoutingLengths() {
        return Map.of(
                "Менее 150", 0,
                "151…200", 1,
                "201…300", 2,
                "301…400", 3,
                "401…600", 4,
                "601…1000", 5,
                "Более 1000", 6
        );
    }
}
