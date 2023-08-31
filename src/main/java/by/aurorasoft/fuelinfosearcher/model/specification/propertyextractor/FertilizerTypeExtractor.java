package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class FertilizerTypeExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "fertilizerType";

    public FertilizerTypeExtractor() {
        super(Specification::findFertilizerType, PROPERTY_NAME);
    }

}
