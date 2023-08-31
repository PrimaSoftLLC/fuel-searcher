package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class WeightRatioGrainToStrawExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "weightRatioGrainToStraw";

    public WeightRatioGrainToStrawExtractor() {
        super(Specification::findWeightRatioGrainToStraw, PROPERTY_NAME);
    }

}
