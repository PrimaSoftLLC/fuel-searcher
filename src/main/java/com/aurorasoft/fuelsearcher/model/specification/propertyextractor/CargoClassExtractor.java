package com.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class CargoClassExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "класс груза";

    public CargoClassExtractor() {
        super(FuelSpecification::findCargoClass, PROPERTY_NAME);
    }
}
