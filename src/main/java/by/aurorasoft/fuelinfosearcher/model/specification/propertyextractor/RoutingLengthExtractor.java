package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class RoutingLengthExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "routingLength";

    public RoutingLengthExtractor() {
        super(Specification::findRoutingLength, PROPERTY_NAME);
    }

}
