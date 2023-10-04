package com.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata;

import com.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.header.RoadGroupHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoadGroupExtractor;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.junit.Test;

import java.util.Map;

import static com.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findProperty;
import static org.junit.Assert.assertSame;

public final class RoadGroupHeaderMetadataFactoryTest {
    private static final String FIELD_NAME_FUEL_OFFSETS_BY_VALUES = "fuelOffsetsByValues";

    private final RoadGroupHeaderMetadataFactory factory = new RoadGroupHeaderMetadataFactory(null);

    @Test
    public void headerMetadataShouldBeCreated() {
        final RoadGroupExtractor givenRoadGroupExtractor = new RoadGroupExtractor();
        final Map<String, Integer> givenFuelOffsetsByHeaders = Map.of(
                "first-header", 1,
                "second-header", 2
        );

        final RoadGroupHeaderMetadata actual = this.factory.create(givenRoadGroupExtractor, givenFuelOffsetsByHeaders);

        final SpecificationPropertyExtractor actualPropertyExtractor = actual.getPropertyExtractor();
        assertSame(givenRoadGroupExtractor, actualPropertyExtractor);

        final Map<String, Integer> actualFuelOffsetsByHeaders = findFuelOffsetsByValues(actual);
        assertSame(givenFuelOffsetsByHeaders, actualFuelOffsetsByHeaders);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Integer> findFuelOffsetsByValues(final FuelHeaderMetadata metadata) {
        return findProperty(
                metadata,
                FIELD_NAME_FUEL_OFFSETS_BY_VALUES,
                Map.class
        );
    }
}
