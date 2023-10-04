package com.aurorasoft.fuelsearcher.configuration;

import com.aurorasoft.fuelsearcher.model.header.RoadGroupHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.header.RoutingLengthHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.header.SpreadRateHeaderMetadata;
import com.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata.RoadGroupHeaderMetadataFactory;
import com.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata.RoutingLengthHeaderMetadataFactory;
import com.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata.SpreadRateHeaderMetadataFactory;
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

    @Bean
    public RoutingLengthHeaderMetadata routingLengthHeaderMetadata(final RoutingLengthHeaderMetadataFactory factory,
                                                                   @Value("${routing-length}") final String[] headerValues) {
        return factory.create(headerValues);
    }

    @Bean
    public SpreadRateHeaderMetadata spreadRateHeaderMetadata(final SpreadRateHeaderMetadataFactory factory,
                                                             @Value("${spread-rate}") final String[] headerValues) {
        return factory.create(headerValues);
    }

}
