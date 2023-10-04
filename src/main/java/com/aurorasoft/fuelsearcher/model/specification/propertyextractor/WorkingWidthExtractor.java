package com.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class WorkingWidthExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "ширина захвата";

    public WorkingWidthExtractor() {
        super(FuelSpecification::findWorkingWidth, PROPERTY_NAME);
    }

}
