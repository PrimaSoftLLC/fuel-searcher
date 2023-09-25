package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.model.SubTableTitleMetadata.SubTableTitleArgumentMetadata;
import by.aurorasoft.fuelsearcher.model.SubTableTitleMetadata.SubTableTitleMetadataBuilder;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.createObject;
import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.setProperty;
import static java.lang.Integer.MIN_VALUE;
import static java.util.Collections.emptyList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class SubTableTitleMetadataTest {
    private static final String FIELD_NAME_TITLE_METADATA_TEMPLATE_WITH_STRING_FILLERS = "templateWithStringFillers";
    private static final String FIELD_NAME_TITLE_METADATA_REGEX = "regex";
    private static final String FIELD_NAME_TITLE_METADATA_ARGUMENTS_METADATA = "argumentsMetadata";

    private static final String FIELD_NAME_ARGUMENT_METADATA_INDEX = "index";
    private static final String FIELD_NAME_ARGUMENT_METADATA_EXTRACTOR = "extractor";

    private static final String FIELD_NAME_TEMPLATE = "template";
    private static final String FIELD_NAME_ARGUMENT_EXTRACTORS = "argumentExtractors";

    @Test
    public void titleMetadataArgumentExtractorsShouldBeFound()
            throws Exception {
        final SubTableTitleMetadata givenTitleMetadata = createTitleMetadata();

        final SpecificationPropertyExtractor firstGivenArgumentMetadataExtractor = mock(
                SpecificationPropertyExtractor.class
        );
        final SubTableTitleArgumentMetadata firstGivenArgumentMetadata = createArgumentMetadata(givenTitleMetadata);
        setExtractor(firstGivenArgumentMetadata, firstGivenArgumentMetadataExtractor);

        final SpecificationPropertyExtractor secondGivenArgumentMetadataExtractor = mock(
                SpecificationPropertyExtractor.class
        );
        final SubTableTitleArgumentMetadata secondGivenArgumentMetadata = createArgumentMetadata(givenTitleMetadata);
        setExtractor(secondGivenArgumentMetadata, secondGivenArgumentMetadataExtractor);

        final List<SubTableTitleArgumentMetadata> givenArgumentsMetadata = List.of(
                firstGivenArgumentMetadata,
                secondGivenArgumentMetadata
        );
        setArgumentsMetadata(givenTitleMetadata, givenArgumentsMetadata);

        final Stream<SpecificationPropertyExtractor> actual = givenTitleMetadata.findArgumentsExtractors();
        final List<SpecificationPropertyExtractor> actualAsList = actual.toList();
        final List<SpecificationPropertyExtractor> expectedAsList = List.of(
                firstGivenArgumentMetadataExtractor,
                secondGivenArgumentMetadataExtractor
        );
        assertEquals(expectedAsList, actualAsList);
    }

    @Test
    public void builderShouldBeCreated() {
        final SubTableTitleMetadataBuilder actual = SubTableTitleMetadata.builder();
        assertNotNull(actual);
    }

    @Test
    public void argumentMetadataPropertyNameShouldBeFound()
            throws Exception {
        final SubTableTitleMetadata givenTitleMetadata = createTitleMetadata();
        final SubTableTitleArgumentMetadata givenArgumentMetadata = createArgumentMetadata(givenTitleMetadata);

        final String givenPropertyName = "property";
        final SpecificationPropertyExtractor givenExtractor = createPropertyExtractor(givenPropertyName);
        setExtractor(givenArgumentMetadata, givenExtractor);

        final String actual = givenArgumentMetadata.findPropertyName();
        assertSame(givenPropertyName, actual);
    }

    @Test
    public void argumentMetadataTitleRegexShouldBeFound()
            throws Exception {
        final String givenTitleMetadataRegex = "regex";
        final SubTableTitleMetadata givenTitleMetadata = createTitleMetadata();
        setRegex(givenTitleMetadata, givenTitleMetadataRegex);

        final SubTableTitleArgumentMetadata givenArgumentMetadata = createArgumentMetadata(givenTitleMetadata);

        final String actual = givenArgumentMetadata.findTitleRegex();
        assertSame(givenTitleMetadataRegex, actual);
    }

    @Test
    public void argumentMetadataGroupIndexInRegexShouldBeFound()
            throws Exception {
        final SubTableTitleMetadata givenTitleMetadata = createTitleMetadata();

        final int givenIndex = 5;
        final SubTableTitleArgumentMetadata givenArgumentMetadata = createArgumentMetadata(givenTitleMetadata);
        setIndex(givenArgumentMetadata, givenIndex);

        final int actual = givenArgumentMetadata.findGroupIndexInRegex();
        final int expected = givenIndex + 1;
        assertEquals(expected, actual);
    }


//    @Test
//    public void templateShouldBeAccumulatedByBuilder()
//            throws Exception {
//        final SubTableTitleMetadataBuilder givenBuilder = SubTableTitleMetadata.builder();
//        final String givenTemplate = "template";
//
//        givenBuilder.templateWithPropertyNames(givenTemplate);
//
//        final String actual = findTemplate(givenBuilder);
//        assertSame(givenTemplate, actual);
//    }
//
//    @Test
//    public void argumentExtractorsShouldBeAccumulatedByBuilder()
//            throws Exception {
//        final SubTableTitleMetadataBuilder givenBuilder = SubTableTitleMetadata.builder();
//
//        final SpecificationPropertyExtractor firstGivenExtractor = mock(SpecificationPropertyExtractor.class);
//        givenBuilder.argumentExtractor(firstGivenExtractor);
//
//        final SpecificationPropertyExtractor secondGivenExtractor = mock(SpecificationPropertyExtractor.class);
//        givenBuilder.argumentExtractor(secondGivenExtractor);
//
//        final List<SpecificationPropertyExtractor> actual = findArgumentExtractors(givenBuilder);
//        final List<SpecificationPropertyExtractor> expected = List.of(firstGivenExtractor, secondGivenExtractor);
//        assertEquals(expected, actual);
//    }

//    @Test
//    public void builderPropertiesShouldBeFound()
//            throws Exception {
//        final SubTableTitleMetadataBuilder givenBuilder = SubTableTitleMetadata.builder();
//
//        final String givenTemplate = "template";
//        setTemplate(givenBuilder, givenTemplate);
//
//        final List<SpecificationPropertyExtractor> givenArgumentExtractors = List.of(
//                mock(SpecificationPropertyExtractor.class),
//                mock(SpecificationPropertyExtractor.class)
//        );
//        setArgumentExtractors(givenBuilder, givenArgumentExtractors);
//
//        final Stream<Object> actual = givenBuilder.findProperties();
//        final List<Object> actualAsList = actual.toList();
//        final List<Object> expectedAsList = List.of(givenTemplate, givenArgumentExtractors);
//        assertEquals(expectedAsList, actualAsList);
//    }
//
//    @Test
//    public void metadataShouldBeBuiltAfterStateValidation()
//            throws Exception {
//        final SubTableTitleMetadataBuilder givenBuilder = SubTableTitleMetadata.builder();
//
//        final String givenTemplate = "template";
//        setTemplate(givenBuilder, givenTemplate);
//
//        final List<SpecificationPropertyExtractor> givenArgumentExtractors = List.of(
//                mock(SpecificationPropertyExtractor.class),
//                mock(SpecificationPropertyExtractor.class)
//        );
//        setArgumentExtractors(givenBuilder, givenArgumentExtractors);
//
//        final SubTableTitleMetadata actual = givenBuilder.buildAfterStateValidation();
//
//        final String actualTemplate = findTemplate(actual);
//        assertSame(givenTemplate, actualTemplate);
//
//        final List<SpecificationPropertyExtractor> actualArgumentExtractors = findArgumentExtractors(actual);
//        assertSame(givenArgumentExtractors, actualArgumentExtractors);
//    }

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

//    @SuppressWarnings("SameParameterValue")
//    private static void setTemplate(final SubTableTitleMetadataBuilder builder, final String template)
//            throws Exception {
//        setProperty(builder, FIELD_NAME_TEMPLATE, template);
//    }
//
//    private static void setArgumentExtractors(final SubTableTitleMetadataBuilder builder,
//                                              final List<SpecificationPropertyExtractor> argumentExtractors)
//            throws Exception {
//        setProperty(builder, FIELD_NAME_ARGUMENT_EXTRACTORS, argumentExtractors);
//    }

    private static void setRegex(final SubTableTitleMetadata titleMetadata, final String regex)
            throws Exception {
        setProperty(
                titleMetadata,
                regex,
                SubTableTitleMetadata.class,
                FIELD_NAME_TITLE_METADATA_REGEX
        );
    }

    private static void setArgumentsMetadata(final SubTableTitleMetadata titleMetadata,
                                             final List<SubTableTitleArgumentMetadata> argumentsMetadata)
            throws Exception {
        setProperty(
                titleMetadata,
                argumentsMetadata,
                SubTableTitleMetadata.class,
                FIELD_NAME_TITLE_METADATA_ARGUMENTS_METADATA
        );
    }

    private static void setExtractor(final SubTableTitleArgumentMetadata argumentMetadata,
                                     final SpecificationPropertyExtractor extractor)
            throws Exception {
        setProperty(
                argumentMetadata,
                extractor,
                SubTableTitleArgumentMetadata.class,
                FIELD_NAME_ARGUMENT_METADATA_EXTRACTOR
        );
    }

    private static void setIndex(final SubTableTitleArgumentMetadata argumentMetadata, final int index)
            throws Exception {
        setProperty(
                argumentMetadata,
                index,
                SubTableTitleArgumentMetadata.class,
                FIELD_NAME_ARGUMENT_METADATA_INDEX
        );
    }

    private static SubTableTitleMetadata createTitleMetadata()
            throws Exception {
        return createObject(
                SubTableTitleMetadata.class,
                new Class<?>[]{String.class, String.class, List.class},
                new Object[]{null, null, emptyList()}
        );
    }

    private static SubTableTitleArgumentMetadata createArgumentMetadata(final SubTableTitleMetadata titleMetadata)
            throws Exception {
        return createObject(
                SubTableTitleArgumentMetadata.class,
                new Class<?>[]{SubTableTitleMetadata.class, int.class, SpecificationPropertyExtractor.class},
                new Object[]{titleMetadata, MIN_VALUE, null}
        );
    }

    private static SpecificationPropertyExtractor createPropertyExtractor(final String propertyName) {
        final SpecificationPropertyExtractor extractor = mock(SpecificationPropertyExtractor.class);
        when(extractor.getPropertyName()).thenReturn(propertyName);
        return extractor;
    }
}
