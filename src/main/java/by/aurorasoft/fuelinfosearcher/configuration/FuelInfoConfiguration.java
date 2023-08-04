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
                        entry(NAME_THIRD_TABLE, createThirdTableFuelInfoOffsetsByRoutingLengths())
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
}
