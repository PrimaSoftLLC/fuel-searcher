package by.aurorasoft.fuelsearcher.service.validator;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.MachineryExtractor;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoadGroupExtractor;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.TractorExtractor;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator.SpecificationValidatorBuilder;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public final class SpecificationValidatorTest {

    @Test
    public void builderShouldBeCreated() {
        final SpecificationValidatorBuilder actual = SpecificationValidator.builder();
        assertNotNull(actual);
    }

    @Test
    public void aliasShouldBeFound()
            throws Exception {
        final String givenTableName = "table-name";
        final SpecificationValidator givenValidator = createValidator(givenTableName, emptyList());

        final String actual = givenValidator.findAlias();
        assertEquals(givenTableName, actual);
    }

    @Test
    public void specificationShouldBeValid()
            throws Exception {
        final String givenTableName = "table";
        final List<SpecificationPropertyExtractor> givenPropertyExtractors = List.of(
                new TractorExtractor(),
                new MachineryExtractor(),
                new RoadGroupExtractor()
        );
        final SpecificationValidator givenValidator = createValidator(givenTableName, givenPropertyExtractors);
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .tableName(givenTableName)
                .tractor("tractor")
                .machinery("machinery")
                .roadGroup("road-group")
                .build();

        final boolean actual = givenValidator.isValid(givenSpecification);
    }

    private static SpecificationValidator createValidator(final String tableName,
                                                          final List<SpecificationPropertyExtractor> propertyExtractors)
            throws Exception {
        final Constructor<SpecificationValidator> constructor = SpecificationValidator.class.getDeclaredConstructor(
                String.class, List.class
        );
        constructor.setAccessible(true);
        try {
            return constructor.newInstance(tableName, propertyExtractors);
        } finally {
            constructor.setAccessible(false);
        }
    }

}
