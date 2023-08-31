package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class SpreadRateExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "spreadRate";

    public SpreadRateExtractor() {
        super(Specification::findSpreadRate, PROPERTY_NAME);
    }

}
