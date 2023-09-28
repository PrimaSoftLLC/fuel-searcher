package by.aurorasoft.fuelsearcher.configuration;

import by.aurorasoft.fuelsearcher.model.header.RoadGroupHeaderMetadata;
import by.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata.RoadGroupHeaderMetadataFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FuelHeaderMetadataConfiguration {

    @Bean
    public RoadGroupHeaderMetadata roadGroupHeaderMetadata(final RoadGroupHeaderMetadataFactory factory,
                                                           @Value("${road-group}") final String[] headerValues) {
        return factory.create(headerValues);
    }

}
