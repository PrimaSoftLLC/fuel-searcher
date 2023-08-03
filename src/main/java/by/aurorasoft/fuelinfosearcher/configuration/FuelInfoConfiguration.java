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

    @Bean
    public FuelDocument document(final FuelDocumentLoadingService loadingService) {
        return loadingService.load();
    }

    @Bean
    public FuelInfoOffsetFromRoutingLengthStorage fuelInfoOffsetFromRoutingLengthStorage() {
        return new FuelInfoOffsetFromRoutingLengthStorage(
                ofEntries(
                        entry(NAME_FIRST_TABLE, createFirstTableFuelInfoOffsetsByRoutingLengths())
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
}
