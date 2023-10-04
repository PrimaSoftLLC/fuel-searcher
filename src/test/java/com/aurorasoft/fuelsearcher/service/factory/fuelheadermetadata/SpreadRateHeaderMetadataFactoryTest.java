package com.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata;

import com.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.header.SpreadRateHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpreadRateExtractor;
import com.aurorasoft.fuelsearcher.testutil.ReflectionUtil;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertSame;

public final class SpreadRateHeaderMetadataFactoryTest {
    private static final String FIELD_NAME_FUEL_OFFSETS_BY_VALUES = "fuelOffsetsByValues";

    private final SpreadRateHeaderMetadataFactory factory = new SpreadRateHeaderMetadataFactory(null);

    @Test
    public void headerMetadataShouldBeCreated() {
        final SpreadRateExtractor givenSpreadRateExtractor = new SpreadRateExtractor();
        final Map<String, Integer> givenFuelOffsetsByHeaders = Map.of(
                "first-header", 1,
                "second-header", 2
        );

        final SpreadRateHeaderMetadata actual = this.factory.create(
                givenSpreadRateExtractor,
                givenFuelOffsetsByHeaders
        );

        final SpecificationPropertyExtractor actualPropertyExtractor = actual.getPropertyExtractor();
        assertSame(givenSpreadRateExtractor, actualPropertyExtractor);

        final Map<String, Integer> actualFuelOffsetsByHeaders = findFuelOffsetsByValues(actual);
        assertSame(givenFuelOffsetsByHeaders, actualFuelOffsetsByHeaders);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Integer> findFuelOffsetsByValues(final FuelHeaderMetadata metadata) {
        return ReflectionUtil.findProperty(
                metadata,
                FIELD_NAME_FUEL_OFFSETS_BY_VALUES,
                Map.class
        );
    }
}
