package com.aurorasoft.fuelsearcher.model.header;

import com.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import com.aurorasoft.fuelsearcher.service.dictionary.Translatable;

import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public abstract class FuelHeaderMetadata extends PropertyMetadataSource implements Translatable {
    private final Map<String, Integer> fuelOffsetsByValues;

    public FuelHeaderMetadata(final SpecificationPropertyExtractor propertyExtractor,
                              final Map<String, Integer> fuelOffsetsByValues) {
        super(propertyExtractor);
        this.fuelOffsetsByValues = fuelOffsetsByValues;
    }

    @Override
    public final String findAlias() {
        return this.findPropertyName();
    }

    public final String[] findHeaderValues() {
        return this.fuelOffsetsByValues
                .keySet()
                .toArray(String[]::new);
    }

    public final Optional<Integer> findFuelOffset(final String headerValue) {
        final Integer fuelOffset = this.fuelOffsetsByValues.get(headerValue);
        return ofNullable(fuelOffset);
    }
}
