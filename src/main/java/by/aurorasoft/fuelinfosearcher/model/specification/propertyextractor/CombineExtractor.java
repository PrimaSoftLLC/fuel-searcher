package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class CombineExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "combine";

    public CombineExtractor() {
        super(Specification::findCombine, PROPERTY_NAME);
    }
}
