//package by.aurorasoft.fuelsearcher.model.header;
//
//import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertSame;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public final class FuelHeaderMetadataTest {
//
//    @Test
//    public void aliasShouldBeFound() {
//        final String givenPropertyName = "property";
//        final SpecificationPropertyExtractor givenHeaderExtractor = createHeaderExtractor(givenPropertyName);
//        final FuelHeaderMetadata givenMetadata = new TestFuelHeaderMetadata(givenHeaderExtractor);
//
//        final String actual = givenMetadata.findAlias();
//        assertEquals(givenPropertyName, actual);
//    }
//
//    @Test
//    public void propertyNameShouldBeFound() {
//        final String givenProperty = "property";
//        final SpecificationPropertyExtractor givenHeaderExtractor = createHeaderExtractor(givenProperty);
//        final FuelHeaderMetadata givenMetadata = new TestFuelHeaderMetadata(givenHeaderExtractor);
//
//        final String actual = givenMetadata.findPropertyName();
//        assertSame(givenProperty, actual);
//    }
//
//    @SuppressWarnings("SameParameterValue")
//    private static SpecificationPropertyExtractor createHeaderExtractor(final String propertyName) {
//        final SpecificationPropertyExtractor extractor = mock(SpecificationPropertyExtractor.class);
//        when(extractor.getPropertyName()).thenReturn(propertyName);
//        return extractor;
//    }
//
//    private static final class TestFuelHeaderMetadata extends FuelHeaderMetadata {
//
//        public TestFuelHeaderMetadata(final SpecificationPropertyExtractor headerExtractor) {
//            super(null, headerExtractor);
//        }
//    }
//}
