package com.aurorasoft.fuelsearcher.configuration;

import com.aurorasoft.fuelsearcher.model.FuelDocument;
import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.SpecificationValidatorsFactory;
import com.aurorasoft.fuelsearcher.service.factory.document.FuelDocumentFactory;
import com.aurorasoft.fuelsearcher.service.metadataloader.TablesMetadataLoadingManager;
import com.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import com.aurorasoft.fuelsearcher.service.searchersparser.FuelSearchersParser;
import com.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource(value = "classpath:fuel-header-metadata.properties", encoding = "UTF-8")
public class FuelConfiguration {

    //TODO: put path in parser and factory test
    @Bean
    public FuelDocument fuelDocument(final FuelDocumentFactory factory,
                                     @Value("${fuel-document.path}") final String filePath) {
        return factory.create(filePath);
    }

    //TODO: put path in parser and refactor test
    @Bean
    public List<FuelSearcher> fuelSearchers(final FuelSearchersParser parser,
                                            @Value("${fuel-searcher-config.path}") final String filePath) {
        return parser.parse(filePath);
    }

    @Bean
    public List<SpecificationValidator> specificationValidators(final SpecificationValidatorsFactory factory) {
        return factory.create();
    }

    //TODO: refactor test
    @Bean
    public List<TableMetadata> tablesMetadata(final TablesMetadataLoadingManager loadingManager) {
        return loadingManager.load();
    }
}
