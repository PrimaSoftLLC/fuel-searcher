package com.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class SowingNormExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "норма высева";

    public SowingNormExtractor() {
        super(FuelSpecification::findSowingNorm, PROPERTY_NAME);
    }

}
