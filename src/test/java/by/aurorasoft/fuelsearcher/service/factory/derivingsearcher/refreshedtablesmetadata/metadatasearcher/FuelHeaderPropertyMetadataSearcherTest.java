package by.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.metadatasearcher;

import by.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class FuelHeaderPropertyMetadataSearcherTest {
    private final FuelHeaderPropertyMetadataSearcher searcher = new FuelHeaderPropertyMetadataSearcher();

    @Test
    public void allowableValuesShouldBeFound() {
        final List<IBodyElement> givenTableElements = emptyList();

        final String[] givenHeaderValues = {"first-value", "second-value", "third-value"};
        final FuelHeaderMetadata givenHeaderMetadata = createHeaderMetadata(givenHeaderValues);

        final Stream<String> actual = this.searcher.findAllowableValues(givenTableElements, givenHeaderMetadata);
        final List<String> actualAsList = actual.toList();
        final List<String> expectedAsList = List.of("first-value", "second-value", "third-value");
        assertEquals(expectedAsList, actualAsList);
    }

    @Test
    public void allowableValuesShouldNotBeDuplicated() {
        final boolean actual = this.searcher.isAllowableValuesDuplicated();
        assertFalse(actual);
    }

    private static FuelHeaderMetadata createHeaderMetadata(final String[] headerValues) {
        final FuelHeaderMetadata metadata = mock(FuelHeaderMetadata.class);
        when(metadata.findHeaderValues()).thenReturn(headerValues);
        return metadata;
    }
}
