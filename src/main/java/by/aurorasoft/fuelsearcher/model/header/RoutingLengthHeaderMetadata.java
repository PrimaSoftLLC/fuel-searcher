package by.aurorasoft.fuelsearcher.model.header;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoutingLengthExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class RoutingLengthHeaderMetadata extends FuelHeaderMetadata {

    public RoutingLengthHeaderMetadata(@Value("${routing-length}") final String[] values,
                                       final RoutingLengthExtractor routingLengthExtractor) {
        super(values, routingLengthExtractor);
    }

}
