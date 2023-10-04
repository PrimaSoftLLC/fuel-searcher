package com.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class RoutingLengthExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "длина гона";

    public RoutingLengthExtractor() {
        super(FuelSpecification::findRoutingLength, PROPERTY_NAME);
    }

}
