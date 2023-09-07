package by.aurorasoft.fuelsearcher.configuration;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.service.documentfactory.FuelDocumentFactory;
import by.aurorasoft.fuelsearcher.service.searchersparser.FuelSearchersParser;
import by.aurorasoft.fuelsearcher.service.searchersparser.SearchersParsingResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:fuel-header-metadata.properties", encoding = "UTF-8")
public class FuelConfiguration {

    @Bean
    public FuelDocument document(final FuelDocumentFactory documentFactory,
                                 @Value("${fuel-document.path}") final String filePath) {
        return documentFactory.create(filePath);
    }

    @Bean
    public SearchersParsingResult searchersParsingResult(final FuelSearchersParser parser,
                                                         @Value("${fuel-searcher-config.path}") final String filePath) {
        return parser.parse(filePath);
    }

}
