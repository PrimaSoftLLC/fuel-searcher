package com.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata;

import com.aurorasoft.fuelsearcher.model.header.RoutingLengthHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoutingLengthExtractor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public final class RoutingLengthHeaderMetadataFactory
        extends FuelHeaderMetadataFactory<RoutingLengthExtractor, RoutingLengthHeaderMetadata> {

    public RoutingLengthHeaderMetadataFactory(final RoutingLengthExtractor routingLengthExtractor) {
        super(routingLengthExtractor);
    }

    @Override
    protected RoutingLengthHeaderMetadata create(final RoutingLengthExtractor routingLengthExtractor,
                                                 final Map<String, Integer> fuelOffsetsByHeaders) {
        return new RoutingLengthHeaderMetadata(routingLengthExtractor, fuelOffsetsByHeaders);
    }
}
