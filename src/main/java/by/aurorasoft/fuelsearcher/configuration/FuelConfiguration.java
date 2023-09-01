package by.aurorasoft.fuelsearcher.configuration;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.service.documentcreating.FuelDocumentCreatingService;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.FuelSearcherDictionary;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.FuelSearcherDictionaryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:fuel-header-metadata.properties", encoding = "UTF-8")
public class FuelConfiguration {

    @Bean
    public FuelDocument document(final FuelDocumentCreatingService creatingService) {
        return creatingService.create();
    }

    @Bean
    public FuelSearcherDictionary fuelSearcherDictionary(final FuelSearcherDictionaryFactory factory) {
        return factory.create("./src/main/resources/fuel-searchers.xml");
    }

}
