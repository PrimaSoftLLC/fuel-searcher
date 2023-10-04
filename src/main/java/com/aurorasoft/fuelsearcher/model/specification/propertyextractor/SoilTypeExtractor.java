package com.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class SoilTypeExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "тип почвы";

    public SoilTypeExtractor() {
        super(FuelSpecification::findSoilType, PROPERTY_NAME);
    }

}
