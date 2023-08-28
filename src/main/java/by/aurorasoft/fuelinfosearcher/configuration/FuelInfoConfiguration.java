package by.aurorasoft.fuelinfosearcher.configuration;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.service.documentcreating.FuelDocumentCreatingService;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.FuelSearchingManager;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.FuelSearchingManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FuelInfoConfiguration {

    @Bean
    public FuelDocument document(final FuelDocumentCreatingService creatingService) {
        return creatingService.create();
    }

    @Bean
    public FuelSearchingManager fuelSearchingManager(final FuelSearchingManagerFactory factory) {
        return factory.create("fuel-searchers.xml");
    }

}
