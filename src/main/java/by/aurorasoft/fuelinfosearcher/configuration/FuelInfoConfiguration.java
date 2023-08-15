package by.aurorasoft.fuelinfosearcher.configuration;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.service.documentcreating.FuelDocumentCreatingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FuelInfoConfiguration {

    @Bean
    public FuelDocument document(final FuelDocumentCreatingService creatingService) {
        return creatingService.create();
    }

}
