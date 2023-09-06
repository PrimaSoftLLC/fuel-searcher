package by.aurorasoft.fuelsearcher.service.validator;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.junit.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class SpecificationValidatingResultTest {

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
                firstGivenExtractor, secondGivenExtractor
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

    private static SpecificationPropertyExtractor createPropertyExtractor(final String propertyName) {
        final SpecificationPropertyExtractor extractor = mock(SpecificationPropertyExtractor.class);
        when(extractor.getPropertyName()).thenReturn(propertyName);
        return extractor;
    }
}
