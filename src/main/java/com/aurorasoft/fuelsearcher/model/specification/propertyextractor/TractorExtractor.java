package com.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class TractorExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "трактор";

    public TractorExtractor() {
        super(FuelSpecification::findTractor, PROPERTY_NAME);
    }

}
