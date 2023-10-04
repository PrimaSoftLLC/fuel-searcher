package com.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class SpecificResistanceExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "удельное сопротивление";

    public SpecificResistanceExtractor() {
        super(FuelSpecification::findSpecificResistance, PROPERTY_NAME);
    }

}
