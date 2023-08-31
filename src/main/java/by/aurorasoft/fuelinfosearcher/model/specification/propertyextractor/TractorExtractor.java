package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class TractorExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "tractor";

    public TractorExtractor() {
        super(Specification::findTractor, PROPERTY_NAME);
    }

}
