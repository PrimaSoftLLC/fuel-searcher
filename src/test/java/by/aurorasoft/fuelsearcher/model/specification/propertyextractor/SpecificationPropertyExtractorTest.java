package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.junit.Test;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class SpecificationPropertyExtractorTest {

    @Test
    public void aliasShouldBeFound() {
        final String givenPropertyName = "property";
        final SpecificationPropertyExtractor givenExtractor = new TestSpecificationPropertyExtractor(givenPropertyName);

        final String actual = givenExtractor.findAlias();
        assertEquals(givenPropertyName, actual);
    }

    @Test
    public void propertyShouldBeFound() {
        final String givenPropertyValue = "property-value";
        final String givenPropertyName = "property-name";
        final SpecificationPropertyExtractor givenExtractor = new TestSpecificationPropertyExtractor(
                givenPropertyValue, givenPropertyName
        );
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenExtractor.find(givenSpecification);
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenPropertyValue, actual);
    }

    @Test
    public void propertyShouldNotBeFound() {
        final String givenPropertyName = "property-name";
        final SpecificationPropertyExtractor givenExtractor = new TestSpecificationPropertyExtractor(givenPropertyName);
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenExtractor.find(givenSpecification);
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void propertyShouldBeExtracted() {
        final String givenPropertyValue = "property-value";
        final String givenPropertyName = "property-name";
        final SpecificationPropertyExtractor givenExtractor = new TestSpecificationPropertyExtractor(
                givenPropertyValue, givenPropertyName
        );
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final String actual = givenExtractor.extract(givenSpecification);
        assertEquals(givenPropertyValue, actual);
    }

    @Test(expected = Exception.class)
    public void propertyShouldNotBeExtracted() {
        final String givenPropertyName = "property-name";
        final SpecificationPropertyExtractor givenExtractor = new TestSpecificationPropertyExtractor(givenPropertyName);
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        givenExtractor.extract(givenSpecification);
    }

    private static final class TestSpecificationPropertyExtractor extends SpecificationPropertyExtractor {

        public TestSpecificationPropertyExtractor(final String propertyValue, final String propertyName) {
            super(specification -> ofNullable(propertyValue), propertyName);
        }

        public TestSpecificationPropertyExtractor(final String propertyName) {
            super(specification -> empty(), propertyName);
        }
    }
}
