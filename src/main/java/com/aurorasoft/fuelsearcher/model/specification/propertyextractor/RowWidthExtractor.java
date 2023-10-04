package com.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class RowWidthExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "ширина междурядий";

    public RowWidthExtractor() {
        super(FuelSpecification::findRowWidth, PROPERTY_NAME);
    }

}
