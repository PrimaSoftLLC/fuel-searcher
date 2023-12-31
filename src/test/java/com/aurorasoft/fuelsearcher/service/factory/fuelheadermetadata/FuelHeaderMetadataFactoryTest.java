package com.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata;

import com.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findProperty;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public final class FuelHeaderMetadataFactoryTest {
    private static final String FIELD_NAME_FUEL_OFFSETS_BY_VALUES = "fuelOffsetsByValues";

    @Test
    public void headerMetadataShouldBeCreated() {
        final SpecificationPropertyExtractor givenPropertyExtractor = mock(SpecificationPropertyExtractor.class);
        final TestFuelHeaderMetadataFactory givenFactory = new TestFuelHeaderMetadataFactory(givenPropertyExtractor);

        final String[] givenHeaderValues = {"value-1", "value-2", "value-3"};

        final FuelHeaderMetadata actual = givenFactory.create(givenHeaderValues);

        final SpecificationPropertyExtractor actualPropertyExtractor = actual.getPropertyExtractor();
        assertSame(givenPropertyExtractor, actualPropertyExtractor);

        final Map<String, Integer> actualFuelOffsetsByHeaders = findFuelOffsetsByValues(actual);
        final Map<String, Integer> expectedFuelOffsetsByHeaders = Map.of(
                "value-1", 0,
                "value-2", 1,
                "value-3", 2
        );
        assertEquals(expectedFuelOffsetsByHeaders, actualFuelOffsetsByHeaders);
        assertTrue(actualFuelOffsetsByHeaders instanceof LinkedHashMap<String, Integer>);
    }

    @Test(expected = IllegalArgumentException.class)
    public void headerMetadataShouldNotBeCreatedBecauseOfDuplicatedHeaderValues() {
        final SpecificationPropertyExtractor givenPropertyExtractor = mock(SpecificationPropertyExtractor.class);
        final TestFuelHeaderMetadataFactory givenFactory = new TestFuelHeaderMetadataFactory(givenPropertyExtractor);

        final String[] givenHeaderValues = {"value-1", "value-2", "value-3", "value-3"};

        givenFactory.create(givenHeaderValues);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Integer> findFuelOffsetsByValues(final FuelHeaderMetadata metadata) {
        return findProperty(
                metadata,
                FIELD_NAME_FUEL_OFFSETS_BY_VALUES,
                Map.class
        );
    }

    private static final class TestFuelHeaderMetadata extends FuelHeaderMetadata {

        public TestFuelHeaderMetadata(final SpecificationPropertyExtractor propertyExtractor,
                                      final Map<String, Integer> fuelOffsetsByValues) {
            super(propertyExtractor, fuelOffsetsByValues);
        }
    }

    private static final class TestFuelHeaderMetadataFactory
            extends FuelHeaderMetadataFactory<SpecificationPropertyExtractor, TestFuelHeaderMetadata> {

        public TestFuelHeaderMetadataFactory(final SpecificationPropertyExtractor propertyExtractor) {
            super(propertyExtractor);
        }

        @Override
        protected TestFuelHeaderMetadata create(final SpecificationPropertyExtractor propertyExtractor,
                                                final Map<String, Integer> fuelOffsetsByHeaders) {
            return new TestFuelHeaderMetadata(propertyExtractor, fuelOffsetsByHeaders);
        }
    }
}
