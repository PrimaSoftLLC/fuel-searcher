package com.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class PloughingDepthExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "глубина вспашки";

    public PloughingDepthExtractor() {
        super(FuelSpecification::findPloughingDepth, PROPERTY_NAME);
    }

}
