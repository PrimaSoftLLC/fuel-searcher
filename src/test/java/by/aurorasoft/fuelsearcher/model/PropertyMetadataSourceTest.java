package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class PropertyMetadataSourceTest {

    @Test
    public void propertyNameShouldBeFound() {
        final String givenPropertyName = "property-name";
        final SpecificationPropertyExtractor givenPropertyExtractor = createPropertyExtractor(givenPropertyName);
        final PropertyMetadataSource givenMetadataSource = new TestPropertyMetadataSource(givenPropertyExtractor);

        final String actual = givenMetadataSource.findPropertyName();
        assertSame(givenPropertyName, actual);
    }

    @SuppressWarnings("SameParameterValue")
    private static SpecificationPropertyExtractor createPropertyExtractor(final String propertyName) {
        final SpecificationPropertyExtractor propertyExtractor = mock(SpecificationPropertyExtractor.class);
        when(propertyExtractor.getPropertyName()).thenReturn(propertyName);
        return propertyExtractor;
    }

    private static final class TestPropertyMetadataSource extends PropertyMetadataSource {

        public TestPropertyMetadataSource(final SpecificationPropertyExtractor propertyExtractor) {
            super(propertyExtractor);
        }
    }

}
