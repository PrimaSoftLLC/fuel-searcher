package by.aurorasoft.fuelsearcher.configuration;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.service.documentfactory.FuelDocumentFactory;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.FuelSearcherDictionary;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.FuelSearcherDictionaryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:fuel-header-metadata.properties", encoding = "UTF-8")
public class FuelConfiguration {

    @Bean
    public FuelDocument document(final FuelDocumentFactory documentFactory) {
        return documentFactory.create("./src/main/resources/postanovlenie128.2022.docx");
    }

    @Bean
    public FuelSearcherDictionary fuelSearcherDictionary(final FuelSearcherDictionaryFactory factory) {
        return factory.create("./src/main/resources/fuel-searchers.xml");
    }

}
