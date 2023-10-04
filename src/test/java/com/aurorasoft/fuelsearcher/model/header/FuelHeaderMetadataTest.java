package com.aurorasoft.fuelsearcher.model.header;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import com.aurorasoft.fuelsearcher.testutil.CollectionUtil;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class FuelHeaderMetadataTest {

    @Test
    public void aliasShouldBeFound() {
        final String givenPropertyName = "property";
        final SpecificationPropertyExtractor givenPropertyExtractor = createPropertyExtractor(givenPropertyName);
        final FuelHeaderMetadata givenMetadata = new TestFuelHeaderMetadata(givenPropertyExtractor);

        final String actual = givenMetadata.findAlias();
        assertEquals(givenPropertyName, actual);
    }

    @Test
    public void headerValuesShouldBeFound() {
        final Map<String, Integer> givenFuelOffsetsByValues = Map.of(
                "first-value", 1,
                "second-value", 2,
                "third-value", 3
        );
        final FuelHeaderMetadata givenMetadata = new TestFuelHeaderMetadata(givenFuelOffsetsByValues);

        final String[] actual = givenMetadata.findHeaderValues();
        final String[] expected = {"first-value", "second-value", "third-value"};
        assertTrue(CollectionUtil.areEqualIgnoringOrder(expected, actual));
    }

    @Test
    public void headerValuesShouldNotBeFound() {
        final Map<String, Integer> givenFuelOffsetsByValues = emptyMap();
        final FuelHeaderMetadata givenMetadata = new TestFuelHeaderMetadata(givenFuelOffsetsByValues);

        final String[] actual = givenMetadata.findHeaderValues();
        assertEquals(0, actual.length);
    }

    @Test
    public void fuelOffsetShouldBeFound() {
        final Map<String, Integer> givenFuelOffsetsByValues = Map.of(
                "first-value", 1,
                "second-value", 2,
                "third-value", 3
        );
        final FuelHeaderMetadata givenMetadata = new TestFuelHeaderMetadata(givenFuelOffsetsByValues);

        final Optional<Integer> optionalActual = givenMetadata.findFuelOffset("second-value");
        assertTrue(optionalActual.isPresent());
        final Integer actual = optionalActual.get();
        assertEquals(2, actual.intValue());
    }

    @Test
    public void fuelOffsetShouldNotBeFound() {
        final Map<String, Integer> givenFuelOffsetsByValues = Map.of(
                "first-value", 1,
                "second-value", 2,
                "third-value", 3
        );
        final FuelHeaderMetadata givenMetadata = new TestFuelHeaderMetadata(givenFuelOffsetsByValues);

        final Optional<Integer> optionalActual = givenMetadata.findFuelOffset("notexisting-value");
        assertTrue(optionalActual.isEmpty());
    }

    @SuppressWarnings("SameParameterValue")
    private static SpecificationPropertyExtractor createPropertyExtractor(final String propertyName) {
        final SpecificationPropertyExtractor extractor = mock(SpecificationPropertyExtractor.class);
        when(extractor.getPropertyName()).thenReturn(propertyName);
        return extractor;
    }

    private static final class TestFuelHeaderMetadata extends FuelHeaderMetadata {

        public TestFuelHeaderMetadata(final SpecificationPropertyExtractor propertyExtractor) {
            super(propertyExtractor, null);
        }

        public TestFuelHeaderMetadata(final Map<String, Integer> fuelOffsetsByValues) {
            super(null, fuelOffsetsByValues);
        }
    }
}
