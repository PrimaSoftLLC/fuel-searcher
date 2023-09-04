package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class YieldExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "урожайность";

    public YieldExtractor() {
        super(FuelSpecification::findYield, PROPERTY_NAME);
    }

}
