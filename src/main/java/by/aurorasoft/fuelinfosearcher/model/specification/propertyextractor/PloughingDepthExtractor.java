package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class PloughingDepthExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "ploughingDepth";

    public PloughingDepthExtractor() {
        super(Specification::findPloughingDepth, PROPERTY_NAME);
    }

}
