package by.aurorasoft.fuelinfosearcher.configuration;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.service.documentloading.FuelDocumentLoadingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FuelInfoConfiguration {

    @Bean
    public FuelDocument document(final FuelDocumentLoadingService loadingService) {
        return loadingService.load();
    }

}
