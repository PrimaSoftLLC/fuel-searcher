package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class SpecificResistanceExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "удельное сопротивление";

    public SpecificResistanceExtractor() {
        super(Specification::findSpecificResistance, PROPERTY_NAME);
    }

}
