package com.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class WeightRatioGrainToStrawExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "соотношение массы зерна к массе соломы";

    public WeightRatioGrainToStrawExtractor() {
        super(FuelSpecification::findWeightRatioGrainToStraw, PROPERTY_NAME);
    }

}
