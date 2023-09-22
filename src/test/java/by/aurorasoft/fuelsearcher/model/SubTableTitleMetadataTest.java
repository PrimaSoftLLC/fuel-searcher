package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.model.SubTableTitleMetadata.SubTableTitleMetadataBuilder;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public final class SubTableTitleMetadataTest {
    private static final String FIELD_NAME_TEMPLATE = "template";
    private static final String FIELD_NAME_ARGUMENT_EXTRACTORS = "argumentExtractors";

    @Test
    public void builderShouldBeCreated() {
        final SubTableTitleMetadataBuilder actual = SubTableTitleMetadata.builder();
        assertNotNull(actual);
    }

    @Test
    public void templateShouldBeAccumulatedByBuilder()
            throws Exception {
        final SubTableTitleMetadataBuilder givenBuilder = SubTableTitleMetadata.builder();
        final String givenTemplate = "template";

        givenBuilder.template(givenTemplate);

        final String actual = findTemplate(givenBuilder);
        assertSame(givenTemplate, actual);
    }

    @Test
    public void argumentExtractorsShouldBeAccumulatedByBuilder()
            throws Exception {
        final SubTableTitleMetadataBuilder givenBuilder = SubTableTitleMetadata.builder();

        final SpecificationPropertyExtractor firstGivenExtractor = mock(SpecificationPropertyExtractor.class);
        givenBuilder.argumentExtractor(firstGivenExtractor);

        final SpecificationPropertyExtractor secondGivenExtractor = mock(SpecificationPropertyExtractor.class);
        givenBuilder.argumentExtractor(secondGivenExtractor);

        final List<SpecificationPropertyExtractor> actual = findArgumentExtractors(givenBuilder);
        final List<SpecificationPropertyExtractor> expected = List.of(firstGivenExtractor, secondGivenExtractor);
        assertEquals(expected, actual);
    }

    @Test
    public void builderPropertiesShouldBeFound()
            throws Exception {
        final SubTableTitleMetadataBuilder givenBuilder = SubTableTitleMetadata.builder();

        final String givenTemplate = "template";
        setTemplate(givenBuilder, givenTemplate);

        final List<SpecificationPropertyExtractor> givenArgumentExtractors = List.of(
                mock(SpecificationPropertyExtractor.class),
                mock(SpecificationPropertyExtractor.class)
        );
        setArgumentExtractors(givenBuilder, givenArgumentExtractors);

        final Stream<Object> actual = givenBuilder.findProperties();
        final List<Object> actualAsList = actual.toList();
        final List<Object> expectedAsList = List.of(givenTemplate, givenArgumentExtractors);
        assertEquals(expectedAsList, actualAsList);
    }

    @Test
    public void metadataShouldBeBuiltAfterStateValidation()
            throws Exception {
        final SubTableTitleMetadataBuilder givenBuilder = SubTableTitleMetadata.builder();

        final String givenTemplate = "template";
        setTemplate(givenBuilder, givenTemplate);

        final List<SpecificationPropertyExtractor> givenArgumentExtractors = List.of(
                mock(SpecificationPropertyExtractor.class),
                mock(SpecificationPropertyExtractor.class)
        );
        setArgumentExtractors(givenBuilder, givenArgumentExtractors);

        final SubTableTitleMetadata actual = givenBuilder.buildAfterStateValidation();

        final String actualTemplate = findTemplate(actual);
        assertSame(givenTemplate, actualTemplate);

        final List<SpecificationPropertyExtractor> actualArgumentExtractors = findArgumentExtractors(actual);
        assertSame(givenArgumentExtractors, actualArgumentExtractors);
    }

    private static String findTemplate(final SubTableTitleMetadataBuilder builder)
            throws Exception {
        return findProperty(
                builder,
                FIELD_NAME_TEMPLATE,
                String.class
        );
    }

    @SuppressWarnings("unchecked")
    private static List<SpecificationPropertyExtractor> findArgumentExtractors(
            final SubTableTitleMetadataBuilder builder)
            throws Exception {
        return findProperty(
                builder,
                FIELD_NAME_ARGUMENT_EXTRACTORS,
                List.class
        );
    }

    private static <P> P findProperty(final SubTableTitleMetadataBuilder builder,
                                      final String fieldName,
                                      final Class<P> propertyType)
            throws Exception {
        return findProperty(
                builder,
                fieldName,
                SubTableTitleMetadataBuilder.class,
                propertyType
        );
    }

    private static String findTemplate(final SubTableTitleMetadata metadata)
            throws Exception {
        return findProperty(
                metadata,
                FIELD_NAME_TEMPLATE,
                String.class
        );
    }

    @SuppressWarnings("unchecked")
    private static List<SpecificationPropertyExtractor> findArgumentExtractors(final SubTableTitleMetadata metadata)
            throws Exception {
        return findProperty(
                metadata,
                FIELD_NAME_ARGUMENT_EXTRACTORS,
                List.class
        );
    }

    private static <P> P findProperty(final SubTableTitleMetadata metadata,
                                      final String fieldName,
                                      final Class<P> propertyType)
            throws Exception {
        return findProperty(
                metadata,
                fieldName,
                SubTableTitleMetadata.class,
                propertyType
        );
    }

    private static <S, P> P findProperty(final S source,
                                         final String fieldName,
                                         final Class<S> sourceType,
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

    @SuppressWarnings("SameParameterValue")
    private static void setTemplate(final SubTableTitleMetadataBuilder builder, final String template)
            throws Exception {
        setProperty(builder, FIELD_NAME_TEMPLATE, template);
    }

    private static void setArgumentExtractors(final SubTableTitleMetadataBuilder builder,
                                              final List<SpecificationPropertyExtractor> argumentExtractors)
            throws Exception {
        setProperty(builder, FIELD_NAME_ARGUMENT_EXTRACTORS, argumentExtractors);
    }

    private static void setProperty(final SubTableTitleMetadataBuilder builder,
                                    final String fieldName,
                                    final Object propertyValue)
            throws Exception {
        final Field field = SubTableTitleMetadataBuilder.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        try {
            field.set(builder, propertyValue);
        } finally {
            field.setAccessible(false);
        }
    }
}
