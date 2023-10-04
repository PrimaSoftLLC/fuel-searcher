package com.aurorasoft.fuelsearcher.service.validator;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.MachineryExtractor;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoadGroupExtractor;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.TractorExtractor;
import com.aurorasoft.fuelsearcher.testutil.ReflectionUtil;
import org.junit.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.*;

public final class SpecificationValidatorTest {
    private static final String FIELD_NAME_RESULT_FAILED_PROPERTY_EXTRACTORS = "failedPropertyExtractors";

    @Test
    public void aliasShouldBeFound() {
        final String givenTableName = "table-name";
        final SpecificationValidator givenValidator = new SpecificationValidator(givenTableName, emptyList());

        final String actual = givenValidator.findAlias();
        assertSame(givenTableName, actual);
    }

    @Test
    public void specificationShouldBeValidatedAsValid() {
        final String givenTableName = "table";
        final List<SpecificationPropertyExtractor> givenPropertyExtractors = List.of(
                new TractorExtractor(),
                new MachineryExtractor(),
                new RoadGroupExtractor()
        );
        final SpecificationValidator givenValidator = new SpecificationValidator(
                givenTableName,
                givenPropertyExtractors
        );
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .tableName(givenTableName)
                .tractor("tractor")
                .machinery("machinery")
                .roadGroup("road-group")
                .build();

        final SpecificationValidatingResult actual = givenValidator.validate(givenSpecification);
        assertTrue(actual.isValid());
    }

    @Test(expected = IllegalArgumentException.class)
    public void specificationShouldNotBeValidatedBecauseOfNotValidTableName() {
        final List<SpecificationPropertyExtractor> givenPropertyExtractors = List.of(
                new TractorExtractor(),
                new MachineryExtractor(),
                new RoadGroupExtractor()
        );
        final SpecificationValidator givenValidator = new SpecificationValidator(
                "table",
                givenPropertyExtractors
        );
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .tableName("wrong-table-name")
                .tractor("tractor")
                .machinery("machinery")
                .roadGroup("road-group")
                .build();

        givenValidator.validate(givenSpecification);
    }

    @Test
    public void specificationShouldBeValidatedAsNotValid() {
        final String givenTableName = "table";
        final SpecificationPropertyExtractor givenMachineryExtractor = new MachineryExtractor();
        final SpecificationPropertyExtractor givenRoadGroupExtractor = new RoadGroupExtractor();
        final List<SpecificationPropertyExtractor> givenPropertyExtractors = List.of(
                new TractorExtractor(),
                givenMachineryExtractor,
                givenRoadGroupExtractor
        );
        final SpecificationValidator givenValidator = new SpecificationValidator(
                givenTableName,
                givenPropertyExtractors
        );
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .tableName(givenTableName)
                .tractor("tractor")
                .build();

        final SpecificationValidatingResult actual = givenValidator.validate(givenSpecification);
        assertFalse(actual.isValid());

        final List<SpecificationPropertyExtractor> expectedFailedPropertyExtractors = List.of(
                givenMachineryExtractor, givenRoadGroupExtractor
        );
        final List<SpecificationPropertyExtractor> actualFailedPropertyExtractors = findFailedPropertyExtractors(
                actual
        );
        assertEquals(expectedFailedPropertyExtractors, actualFailedPropertyExtractors);
    }

    @SuppressWarnings("unchecked")
    private static List<SpecificationPropertyExtractor> findFailedPropertyExtractors(
            final SpecificationValidatingResult validatingResult
    ) {
        return ReflectionUtil.findProperty(
                validatingResult,
                FIELD_NAME_RESULT_FAILED_PROPERTY_EXTRACTORS,
                List.class
        );
    }
}
