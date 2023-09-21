package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher;

import by.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.junit.Test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class FuelHeaderPropertyMetadataSearcherTest {

    @Test
    public void propertyNameShouldBeFound() {
        final FuelHeaderPropertyMetadataSearcher givenSearcher = new FuelHeaderPropertyMetadataSearcher();

        final String givenPropertyName = "property-name";
        final FuelHeaderMetadata givenHeaderMetadata = createHeaderMetadata(givenPropertyName);

        final String actual = givenSearcher.findPropertyName(givenHeaderMetadata);
        assertSame(givenPropertyName, actual);
    }

    @Test
    public void allowableValuesShouldBeFound() {
        final FuelHeaderPropertyMetadataSearcher givenSearcher = new FuelHeaderPropertyMetadataSearcher();

        final List<IBodyElement> givenTableElements = emptyList();

        final String[] givenAllowableValues = {"first-value", "second-value", "third-value"};
        final FuelHeaderMetadata givenHeaderMetadata = createHeaderMetadata(givenAllowableValues);

        final Stream<String> actual = givenSearcher.findAllowableValues(givenTableElements, givenHeaderMetadata);
        final List<String> actualAsList = actual.toList();
        final List<String> expectedAsList = List.of("first-value", "second-value", "third-value");
        assertEquals(expectedAsList, actualAsList);
    }

    @Test
    public void allowableValuesShouldNotBeDuplicated() {
        final FuelHeaderPropertyMetadataSearcher givenSearcher = new FuelHeaderPropertyMetadataSearcher();

        final boolean actual = givenSearcher.isAllowableValuesDuplicated();
        assertFalse(actual);
    }

    @SuppressWarnings("SameParameterValue")
    private static FuelHeaderMetadata createHeaderMetadata(final String propertyName) {
        return createHeaderMetadata(
                propertyName,
                FuelHeaderMetadata::findPropertyName
        );
    }

    private static FuelHeaderMetadata createHeaderMetadata(final String[] allowableValues) {
        return createHeaderMetadata(
                allowableValues,
                FuelHeaderMetadata::getValues
        );
    }

    private static <P> FuelHeaderMetadata createHeaderMetadata(final P property,
                                                               final Function<FuelHeaderMetadata, P> propertyExtractor) {
        final FuelHeaderMetadata metadata = mock(FuelHeaderMetadata.class);
        when(propertyExtractor.apply(metadata)).thenReturn(property);
        return metadata;
    }
}
