package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class SpreadRateExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "норма внесения";

    public SpreadRateExtractor() {
        super(FuelSpecification::findSpreadRate, PROPERTY_NAME);
    }

}
