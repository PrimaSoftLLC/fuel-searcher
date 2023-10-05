package com.aurorasoft.fuelsearcher.service.validator;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.junit.Test;

import java.util.List;

import static com.aurorasoft.fuelsearcher.service.validator.SpecificationValidatingResult.createNotValidValidatingResult;
import static com.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findProperty;
import static java.util.Collections.emptyList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class SpecificationValidatingResultTest {
    private static final String FIELD_NAME_FAILED_PROPERTY_EXTRACTORS = "failedPropertyExtractors";

    @Test
    public void validatingResultShouldBeValid() {
        final List<SpecificationPropertyExtractor> givenPropertyExtractors = emptyList();
        final SpecificationValidatingResult givenValidatingResult = new SpecificationValidatingResult(
                givenPropertyExtractors
        );

        final boolean actual = givenValidatingResult.isValid();
        assertTrue(actual);
    }

    @Test
    public void validatingResultShouldNotBeValid() {
        final List<SpecificationPropertyExtractor> givenPropertyExtractors = List.of(
                mock(SpecificationPropertyExtractor.class),
                mock(SpecificationPropertyExtractor.class)
        );
        final SpecificationValidatingResult givenValidatingResult = new SpecificationValidatingResult(
                givenPropertyExtractors
        );

        final boolean actual = givenValidatingResult.isValid();
        assertFalse(actual);
    }

    @Test
    public void failedPropertyNamesShouldBeFound() {
        final String firstGivenPropertyName = "first-property";
        final SpecificationPropertyExtractor firstGivenExtractor = createPropertyExtractor(firstGivenPropertyName);

        final String secondGivenPropertyName = "second-property";
        final SpecificationPropertyExtractor secondGivenExtractor = createPropertyExtractor(secondGivenPropertyName);

        final List<SpecificationPropertyExtractor> givenPropertyExtractors = List.of(
                firstGivenExtractor,
                secondGivenExtractor
        );
        final SpecificationValidatingResult givenValidatingResult = new SpecificationValidatingResult(
                givenPropertyExtractors
        );

        final List<String> actual = givenValidatingResult.findFailedPropertyNames();
        final List<String> expected = List.of(firstGivenPropertyName, secondGivenPropertyName);
        assertEquals(expected, actual);
    }

    @Test
    public void emptyFailedPropertyNamesShouldBeFound() {
        final List<SpecificationPropertyExtractor> givenPropertyExtractors = emptyList();
        final SpecificationValidatingResult givenValidatingResult = new SpecificationValidatingResult(
                givenPropertyExtractors
        );

        final List<String> actual = givenValidatingResult.findFailedPropertyNames();
        assertTrue(actual.isEmpty());
    }

    @Test
    public void validatingResultShouldBeCreatedByFailedPropertyExtractor() {
        final SpecificationPropertyExtractor givenExtractor = mock(SpecificationPropertyExtractor.class);

        final SpecificationValidatingResult actual = createNotValidValidatingResult(givenExtractor);

        final List<SpecificationPropertyExtractor> actualFailedPropertyExtractors = findFailedPropertyExtractors(
                actual
        );
        final List<SpecificationPropertyExtractor> expectedFailedPropertyExtractors = List.of(givenExtractor);
        assertEquals(expectedFailedPropertyExtractors, actualFailedPropertyExtractors);
    }

    private static SpecificationPropertyExtractor createPropertyExtractor(final String propertyName) {
        final SpecificationPropertyExtractor extractor = mock(SpecificationPropertyExtractor.class);
        when(extractor.getPropertyName()).thenReturn(propertyName);
        return extractor;
    }

    @SuppressWarnings("unchecked")
    private static List<SpecificationPropertyExtractor> findFailedPropertyExtractors(
            final SpecificationValidatingResult validatingResult
    ) {
        return findProperty(
                validatingResult,
                FIELD_NAME_FAILED_PROPERTY_EXTRACTORS,
                List.class
        );
    }
}
