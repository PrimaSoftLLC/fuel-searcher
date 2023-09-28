package by.aurorasoft.fuelsearcher.model.header;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoutingLengthExtractor;

import java.util.Map;

public final class RoutingLengthHeaderMetadata extends FuelHeaderMetadata {

    public RoutingLengthHeaderMetadata(final RoutingLengthExtractor routingLengthExtractor,
                                       final Map<String, Integer> fuelOffsetsByValues) {
        super(routingLengthExtractor, fuelOffsetsByValues);
    }

}
