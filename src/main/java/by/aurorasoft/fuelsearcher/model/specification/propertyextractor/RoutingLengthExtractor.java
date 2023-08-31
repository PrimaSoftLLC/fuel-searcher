package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class RoutingLengthExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "длина гона";

    public RoutingLengthExtractor() {
        super(Specification::findRoutingLength, PROPERTY_NAME);
    }

}
