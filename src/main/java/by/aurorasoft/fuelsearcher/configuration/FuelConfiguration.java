package by.aurorasoft.fuelsearcher.configuration;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.service.factory.derivingsearcherfactory.SpecificationValidatorsFactory;
import by.aurorasoft.fuelsearcher.service.factory.documentfactory.FuelDocumentFactory;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.searchersparser.FuelSearchersParser;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource(value = "classpath:fuel-header-metadata.properties", encoding = "UTF-8")
public class FuelConfiguration {

    @Bean
    public FuelDocument fuelDocument(final FuelDocumentFactory factory,
                                     @Value("${fuel-document.path}") final String filePath) {
        return factory.create(filePath);
    }

    @Bean
    public List<FuelSearcher> fuelSearchers(final FuelSearchersParser parser,
                                            @Value("${fuel-searcher-config.path}") final String filePath) {
        return parser.parse(filePath);
    }

    @Bean
    public List<SpecificationValidator> specificationValidators(final SpecificationValidatorsFactory factory) {
        return factory.create();
    }

}
