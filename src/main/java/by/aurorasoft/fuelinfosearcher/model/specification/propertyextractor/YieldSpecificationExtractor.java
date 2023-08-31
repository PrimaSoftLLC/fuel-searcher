package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class YieldSpecificationExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "yield";

    public YieldSpecificationExtractor() {
        super(Specification::findYield, PROPERTY_NAME);
    }

}
