package by.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata;

import by.aurorasoft.fuelsearcher.model.header.RoutingLengthHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoutingLengthExtractor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public final class RoutingLengthHeaderMetadataFactory
        extends FuelHeaderMetadataFactory<RoutingLengthExtractor, RoutingLengthHeaderMetadata> {

    public RoutingLengthHeaderMetadataFactory(final RoutingLengthExtractor propertyExtractor) {
        super(propertyExtractor);
    }

    @Override
    protected RoutingLengthHeaderMetadata create(final RoutingLengthExtractor propertyExtractor,
                                                 final Map<String, Integer> fuelOffsetsByHeaders) {
        return new RoutingLengthHeaderMetadata(propertyExtractor, fuelOffsetsByHeaders);
    }
}
