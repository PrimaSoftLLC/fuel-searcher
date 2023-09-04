package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class FertilizerTypeExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "тип удобрения";

    public FertilizerTypeExtractor() {
        super(FuelSpecification::findFertilizerType, PROPERTY_NAME);
    }

}
