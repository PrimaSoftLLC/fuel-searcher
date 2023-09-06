package by.aurorasoft.fuelsearcher.service.validator;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.MachineryExtractor;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoadGroupExtractor;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.TractorExtractor;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator.SpecificationValidatorBuilder;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.service.validator.SpecificationValidatingResult.createNotValidValidatingResult;
import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public final class SpecificationValidatorTest {
    private static final String FIELD_NAME_FAILED_PROPERTY_EXTRACTORS = "failedPropertyExtractors";
    private static final String FIELD_NAME_REQUIRED_PROPERTY_EXTRACTORS = "requiredPropertyExtractors";
    private static final String FIELD_NAME_TABLE_NAME = "tableName";

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
    public void specificationShouldBeValidatedAsValid()
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

        final SpecificationValidatingResult actual = givenValidator.validate(givenSpecification);
        assertTrue(actual.isValid());
    }

    @Test(expected = IllegalArgumentException.class)
    public void specificationShouldNotBeValidatedBecauseOfNotValidTableName()
            throws Exception {
        final List<SpecificationPropertyExtractor> givenPropertyExtractors = List.of(
                new TractorExtractor(),
                new MachineryExtractor(),
                new RoadGroupExtractor()
        );
        final SpecificationValidator givenValidator = createValidator("table", givenPropertyExtractors);
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .tableName("wrong-table-name")
                .tractor("tractor")
                .machinery("machinery")
                .roadGroup("road-group")
                .build();

        givenValidator.validate(givenSpecification);
    }

    @Test
    public void specificationShouldBeValidatedAsNotValid()
            throws Exception {
        final String givenTableName = "table";
        final SpecificationPropertyExtractor givenMachineryExtractor = new MachineryExtractor();
        final SpecificationPropertyExtractor givenRoadGroupExtractor = new RoadGroupExtractor();
        final List<SpecificationPropertyExtractor> givenPropertyExtractors = List.of(
                new TractorExtractor(),
                givenMachineryExtractor,
                givenRoadGroupExtractor
        );
        final SpecificationValidator givenValidator = createValidator(givenTableName, givenPropertyExtractors);
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

    @Test
    public void tableNameShouldBeAccumulatedByBuilder()
            throws Exception {
        final SpecificationValidatorBuilder givenBuilder = SpecificationValidator.builder();
        final String givenTableName = "table-name";

        givenBuilder.tableName(givenTableName);

        final String actual = findTableName(givenBuilder);
        assertEquals(givenTableName, actual);
    }

    @Test
    public void requiredPropertyExtractorsShouldBeAccumulatedByBuilder()
            throws Exception {
        final SpecificationValidatorBuilder givenBuilder = SpecificationValidator.builder();
        final SpecificationPropertyExtractor firstGivenPropertyExtractor = mock(SpecificationPropertyExtractor.class);
        final SpecificationPropertyExtractor secondGivenPropertyExtractor = mock(SpecificationPropertyExtractor.class);

        givenBuilder.requiredPropertyExtractor(firstGivenPropertyExtractor);
        givenBuilder.requiredPropertyExtractor(secondGivenPropertyExtractor);

        final List<SpecificationPropertyExtractor> actual = findRequiredPropertyExtractors(givenBuilder);
        final List<SpecificationPropertyExtractor> expected = List.of(
                firstGivenPropertyExtractor, secondGivenPropertyExtractor
        );
        assertEquals(expected, actual);
    }

    @Test
    public void builderPropertiesShouldBeFound() {
        final SpecificationValidatorBuilder givenBuilder = SpecificationValidator.builder();

        final String givenTableName = "table-name";
        givenBuilder.tableName(givenTableName);

        final SpecificationPropertyExtractor firstGivenPropertyExtractor = mock(SpecificationPropertyExtractor.class);
        final SpecificationPropertyExtractor secondGivenPropertyExtractor = mock(SpecificationPropertyExtractor.class);
        givenBuilder.requiredPropertyExtractor(firstGivenPropertyExtractor);
        givenBuilder.requiredPropertyExtractor(secondGivenPropertyExtractor);

        final Stream<Object> actual = givenBuilder.findProperties();
        final List<Object> actualAsList = actual.toList();
        final List<Object> expectedAsList = List.of(
                givenTableName,
                List.of(firstGivenPropertyExtractor, secondGivenPropertyExtractor)
        );
        assertEquals(expectedAsList, actualAsList);
    }

    @Test
    public void validatorShouldBeBuilt()
            throws Exception {
        final SpecificationValidatorBuilder givenBuilder = SpecificationValidator.builder();

        final String givenTableName = "table-name";
        givenBuilder.tableName(givenTableName);

        final SpecificationPropertyExtractor firstGivenPropertyExtractor = mock(SpecificationPropertyExtractor.class);
        final SpecificationPropertyExtractor secondGivenPropertyExtractor = mock(SpecificationPropertyExtractor.class);
        givenBuilder.requiredPropertyExtractor(firstGivenPropertyExtractor);
        givenBuilder.requiredPropertyExtractor(secondGivenPropertyExtractor);

        final SpecificationValidator actual = givenBuilder.buildAfterStateValidation();

        final String actualTableName = findTableName(actual);
        assertSame(givenTableName, actualTableName);

        final List<SpecificationPropertyExtractor> actualRequiredPropertyExtractors = findRequiredPropertyExtractors(
                actual
        );
        assertTrue(isImmutableList(actualRequiredPropertyExtractors));

        final List<SpecificationPropertyExtractor> expectedRequiredPropertyExtractors = List.of(
                firstGivenPropertyExtractor, secondGivenPropertyExtractor
        );
        assertEquals(expectedRequiredPropertyExtractors, actualRequiredPropertyExtractors);
    }

    @Test
    public void validatingResultShouldBeCreatedByFailedPropertyExtractor()
            throws Exception {
        final SpecificationPropertyExtractor givenExtractor = mock(SpecificationPropertyExtractor.class);

        final SpecificationValidatingResult actual = createNotValidValidatingResult(givenExtractor);

        final List<SpecificationPropertyExtractor> actualFailedPropertyExtractors = findFailedPropertyExtractors(
                actual
        );
        final List<SpecificationPropertyExtractor> expectedFailedPropertyExtractors = List.of(givenExtractor);
        assertEquals(expectedFailedPropertyExtractors, actualFailedPropertyExtractors);
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

    @SuppressWarnings("unchecked")
    private static List<SpecificationPropertyExtractor> findFailedPropertyExtractors(
            final SpecificationValidatingResult validatingResult)
            throws Exception {
        return findProperty(
                validatingResult,
                SpecificationValidatingResult.class,
                FIELD_NAME_FAILED_PROPERTY_EXTRACTORS,
                List.class
        );
    }

    @SuppressWarnings("unchecked")
    private static List<SpecificationPropertyExtractor> findRequiredPropertyExtractors(
            final SpecificationValidatorBuilder builder)
            throws Exception {
        return findProperty(
                builder,
                SpecificationValidatorBuilder.class,
                FIELD_NAME_REQUIRED_PROPERTY_EXTRACTORS,
                List.class
        );
    }

    @SuppressWarnings("unchecked")
    private static List<SpecificationPropertyExtractor> findRequiredPropertyExtractors(
            final SpecificationValidator validator)
            throws Exception {
        return findProperty(
                validator,
                SpecificationValidator.class,
                FIELD_NAME_REQUIRED_PROPERTY_EXTRACTORS,
                List.class
        );
    }

    private static String findTableName(final SpecificationValidatorBuilder builder)
            throws Exception {
        return findProperty(
                builder,
                SpecificationValidatorBuilder.class,
                FIELD_NAME_TABLE_NAME,
                String.class
        );
    }

    private static String findTableName(final SpecificationValidator validator)
            throws Exception {
        return findProperty(
                validator,
                SpecificationValidator.class,
                FIELD_NAME_TABLE_NAME,
                String.class
        );
    }

    private static <S, P> P findProperty(final S source,
                                         final Class<S> sourceType,
                                         final String fieldName,
                                         final Class<P> propertyType)
            throws Exception {
        final Field field = sourceType.getDeclaredField(fieldName);
        field.setAccessible(true);
        try {
            final Object property = field.get(source);
            return propertyType.cast(property);
        } finally {
            field.setAccessible(false);
        }
    }

    private static <T> boolean isImmutableList(final List<T> research) {
        final List<T> tempImmutableList = unmodifiableList(new ArrayList<>());
        final Class<?> typeImmutableList = tempImmutableList.getClass();
        return typeImmutableList.isInstance(research);
    }
}
