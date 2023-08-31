package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class TransportDistanceExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "transportDistance";

    public TransportDistanceExtractor() {
        super(Specification::findTransportDistance, PROPERTY_NAME);
    }
}
