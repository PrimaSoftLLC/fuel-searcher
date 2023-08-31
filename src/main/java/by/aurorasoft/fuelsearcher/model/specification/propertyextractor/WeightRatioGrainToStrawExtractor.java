package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class WeightRatioGrainToStrawExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "соотношение массы зерна к массе соломы";

    public WeightRatioGrainToStrawExtractor() {
        super(Specification::findWeightRatioGrainToStraw, PROPERTY_NAME);
    }

}
