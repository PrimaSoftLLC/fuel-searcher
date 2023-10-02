package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.model.SubTableTitleMetadata.SubTableTitleArgumentMetadata;
import by.aurorasoft.fuelsearcher.model.SubTableTitleMetadata.SubTableTitleMetadataBuilder;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.util.SubTableTitleUtil;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.*;
import static by.aurorasoft.fuelsearcher.util.SubTableTitleUtil.findTemplateRegex;
import static by.aurorasoft.fuelsearcher.util.SubTableTitleUtil.findTemplateWithStringFillers;
import static java.lang.Integer.MIN_VALUE;
import static java.util.Collections.emptyList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public final class SubTableTitleMetadataTest {
    private static final String FIELD_NAME_TITLE_METADATA_REGEX = "regex";
    private static final String FIELD_NAME_TITLE_METADATA_ARGUMENTS_METADATA = "argumentsMetadata";

    private static final String FIELD_NAME_PROPERTY_METADATA_SOURCE_PROPERTY_EXTRACTOR = "propertyExtractor";

    private static final String FIELD_NAME_ARGUMENT_METADATA_INDEX = "index";

    private static final String FIELD_NAME_BUILDER_TEMPLATE_WITH_PROPERTY_NAMES = "templateWithPropertyNames";
    private static final String FIELD_NAME_BUILDER_ARGUMENT_EXTRACTORS = "argumentExtractors";

    @Test
    public void titleMetadataArgumentExtractorsShouldBeFound() {
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
    public void argumentMetadataPropertyNameShouldBeFound() {
        final SubTableTitleMetadata givenTitleMetadata = createTitleMetadata();
        final SubTableTitleArgumentMetadata givenArgumentMetadata = createArgumentMetadata(givenTitleMetadata);

        final String givenPropertyName = "property";
        final SpecificationPropertyExtractor givenExtractor = createPropertyExtractor(givenPropertyName);
        setExtractor(givenArgumentMetadata, givenExtractor);

        final String actual = givenArgumentMetadata.findPropertyName();
        assertSame(givenPropertyName, actual);
    }

    @Test
    public void argumentMetadataTitleRegexShouldBeFound() {
        final String givenTitleMetadataRegex = "regex";
        final SubTableTitleMetadata givenTitleMetadata = createTitleMetadata();
        setRegex(givenTitleMetadata, givenTitleMetadataRegex);

        final SubTableTitleArgumentMetadata givenArgumentMetadata = createArgumentMetadata(givenTitleMetadata);

        final String actual = givenArgumentMetadata.findTitleRegex();
        assertSame(givenTitleMetadataRegex, actual);
    }

    @Test
    public void argumentMetadataGroupIndexInRegexShouldBeFound() {
        final SubTableTitleMetadata givenTitleMetadata = createTitleMetadata();

        final int givenIndex = 5;
        final SubTableTitleArgumentMetadata givenArgumentMetadata = createArgumentMetadata(givenTitleMetadata);
        setIndex(givenArgumentMetadata, givenIndex);

        final int actual = givenArgumentMetadata.findGroupIndexInRegex();
        final int expected = givenIndex + 1;
        assertEquals(expected, actual);
    }

    @Test
    public void templateWithPropertyNamesShouldBeAccumulatedByBuilder() {
        final SubTableTitleMetadataBuilder givenBuilder = SubTableTitleMetadata.builder();
        final String givenTemplate = "{трактор} с {механизм}";

        givenBuilder.templateWithPropertyNames(givenTemplate);

        final String actual = findTemplateWithPropertyNames(givenBuilder);
        assertSame(givenTemplate, actual);
    }

    @Test
    public void argumentExtractorsShouldBeAccumulatedByBuilder() {
        final SubTableTitleMetadataBuilder givenBuilder = SubTableTitleMetadata.builder();

        final SpecificationPropertyExtractor firstGivenExtractor = mock(SpecificationPropertyExtractor.class);
        givenBuilder.argumentExtractor(firstGivenExtractor);

        final SpecificationPropertyExtractor secondGivenExtractor = mock(SpecificationPropertyExtractor.class);
        givenBuilder.argumentExtractor(secondGivenExtractor);

        final List<SpecificationPropertyExtractor> actual = findArgumentExtractors(givenBuilder);
        final List<SpecificationPropertyExtractor> expected = List.of(
                firstGivenExtractor,
                secondGivenExtractor
        );
        assertEquals(expected, actual);
    }

    @Test
    public void propertiesShouldBeFound() {
        final SubTableTitleMetadataBuilder givenBuilder = SubTableTitleMetadata.builder();

        final String givenTemplateWithPropertyNames = "{трактор} с {механизм}";
        setTemplateWithPropertyNames(givenBuilder, givenTemplateWithPropertyNames);

        final List<SpecificationPropertyExtractor> givenArgumentExtractors = List.of(
                mock(SpecificationPropertyExtractor.class),
                mock(SpecificationPropertyExtractor.class)
        );
        setArgumentExtractors(givenBuilder, givenArgumentExtractors);

        final Stream<Object> actual = givenBuilder.findProperties();
        final List<Object> actualAsList = actual.toList();
        final List<Object> expectedAsList = List.of(givenTemplateWithPropertyNames, givenArgumentExtractors);
        assertEquals(expectedAsList, actualAsList);
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void titleMetadataShouldBeBuiltAfterStateValidation() {
        try (final MockedStatic<SubTableTitleUtil> mockedTitleUtil = mockStatic(SubTableTitleUtil.class)) {
            final SubTableTitleMetadataBuilder givenBuilder = SubTableTitleMetadata.builder();

            final String givenTemplateWithPropertyNames = "{трактор} с {механизм}";
            setTemplateWithPropertyNames(givenBuilder, givenTemplateWithPropertyNames);

            final SpecificationPropertyExtractor firstGivenArgumentExtractor = mock(
                    SpecificationPropertyExtractor.class
            );
            final SpecificationPropertyExtractor secondGivenArgumentExtractor = mock(
                    SpecificationPropertyExtractor.class
            );
            final List<SpecificationPropertyExtractor> givenArgumentExtractors = List.of(
                    firstGivenArgumentExtractor,
                    secondGivenArgumentExtractor
            );
            setArgumentExtractors(givenBuilder, givenArgumentExtractors);

            final String givenTemplateWithStringFillers = "%s с %s";
            mockedTitleUtil.when(() -> findTemplateWithStringFillers(same(givenTemplateWithPropertyNames)))
                    .thenReturn(givenTemplateWithStringFillers);

            final String givenTemplateRegex = "(.+) с (.+)";
            mockedTitleUtil.when(() -> findTemplateRegex(same(givenTemplateWithPropertyNames)))
                    .thenReturn(givenTemplateRegex);

            final SubTableTitleMetadata actual = givenBuilder.buildAfterStateValidation();

            final String actualTemplateWithStringFillers = actual.getTemplateWithStringFillers();
            assertSame(givenTemplateWithStringFillers, actualTemplateWithStringFillers);

            final String actualRegex = findRegex(actual);
            assertSame(givenTemplateRegex, actualRegex);

            final List<SubTableTitleArgumentMetadata> actualArgumentsMetadata = actual.getArgumentsMetadata();
            assertEquals(2, actualArgumentsMetadata.size());

            final SubTableTitleArgumentMetadata actualFirstArgumentMetadata = actualArgumentsMetadata.get(0);

            final int actualFirstArgumentMetadataIndex = findIndex(actualFirstArgumentMetadata);
            final int expectedFirstArgumentMetadataIndex = 0;
            assertEquals(expectedFirstArgumentMetadataIndex, actualFirstArgumentMetadataIndex);

            final SpecificationPropertyExtractor actualFirstArgumentMetadataExtractor = findExtractor(
                    actualFirstArgumentMetadata
            );
            assertSame(firstGivenArgumentExtractor, actualFirstArgumentMetadataExtractor);

            final SubTableTitleArgumentMetadata actualSecondArgumentMetadata = actualArgumentsMetadata.get(1);

            final int actualSecondArgumentMetadataIndex = findIndex(actualSecondArgumentMetadata);
            final int expectedSecondArgumentMetadataIndex = 1;
            assertEquals(expectedSecondArgumentMetadataIndex, actualSecondArgumentMetadataIndex);

            final SpecificationPropertyExtractor actualSecondArgumentMetadataExtractor = findExtractor(
                    actualSecondArgumentMetadata
            );
            assertSame(secondGivenArgumentExtractor, actualSecondArgumentMetadataExtractor);
        }
    }

    private static SubTableTitleMetadata createTitleMetadata() {
        return createObject(
                SubTableTitleMetadata.class,
                new Class<?>[]{String.class, String.class, List.class},
                new Object[]{null, null, emptyList()}
        );
    }

    private static SubTableTitleArgumentMetadata createArgumentMetadata(final SubTableTitleMetadata titleMetadata) {
        return createObject(
                SubTableTitleArgumentMetadata.class,
                new Class<?>[]{SubTableTitleMetadata.class, SpecificationPropertyExtractor.class, int.class},
                new Object[]{titleMetadata, null, MIN_VALUE}
        );
    }

    @SuppressWarnings("SameParameterValue")
    private static SpecificationPropertyExtractor createPropertyExtractor(final String propertyName) {
        final SpecificationPropertyExtractor extractor = mock(SpecificationPropertyExtractor.class);
        when(extractor.getPropertyName()).thenReturn(propertyName);
        return extractor;
    }

    @SuppressWarnings("SameParameterValue")
    private static void setRegex(final SubTableTitleMetadata titleMetadata, final String regex) {
        setProperty(
                titleMetadata,
                regex,
                SubTableTitleMetadata.class,
                FIELD_NAME_TITLE_METADATA_REGEX
        );
    }

    private static void setArgumentsMetadata(final SubTableTitleMetadata titleMetadata,
                                             final List<SubTableTitleArgumentMetadata> argumentsMetadata) {
        setProperty(
                titleMetadata,
                argumentsMetadata,
                SubTableTitleMetadata.class,
                FIELD_NAME_TITLE_METADATA_ARGUMENTS_METADATA
        );
    }

    private static void setExtractor(final PropertyMetadataSource propertyMetadataSource,
                                     final SpecificationPropertyExtractor extractor) {
        setProperty(
                propertyMetadataSource,
                extractor,
                PropertyMetadataSource.class,
                FIELD_NAME_PROPERTY_METADATA_SOURCE_PROPERTY_EXTRACTOR
        );
    }

    @SuppressWarnings("SameParameterValue")
    private static void setIndex(final SubTableTitleArgumentMetadata argumentMetadata, final int index) {
        setProperty(
                argumentMetadata,
                index,
                SubTableTitleArgumentMetadata.class,
                FIELD_NAME_ARGUMENT_METADATA_INDEX
        );
    }

    @SuppressWarnings("SameParameterValue")
    private static void setTemplateWithPropertyNames(final SubTableTitleMetadataBuilder builder, final String template) {
        setProperty(
                builder,
                template,
                SubTableTitleMetadataBuilder.class,
                FIELD_NAME_BUILDER_TEMPLATE_WITH_PROPERTY_NAMES
        );
    }

    private static void setArgumentExtractors(final SubTableTitleMetadataBuilder builder,
                                              final List<SpecificationPropertyExtractor> extractors) {
        setProperty(
                builder,
                extractors,
                SubTableTitleMetadataBuilder.class,
                FIELD_NAME_BUILDER_ARGUMENT_EXTRACTORS
        );
    }

    private static String findTemplateWithPropertyNames(final SubTableTitleMetadataBuilder builder) {
        return findProperty(
                builder,
                FIELD_NAME_BUILDER_TEMPLATE_WITH_PROPERTY_NAMES,
                String.class
        );
    }

    private static String findRegex(final SubTableTitleMetadata metadata) {
        return findProperty(
                metadata,
                FIELD_NAME_TITLE_METADATA_REGEX,
                String.class
        );
    }

    private static int findIndex(final SubTableTitleArgumentMetadata metadata) {
        return findProperty(
                metadata,
                FIELD_NAME_ARGUMENT_METADATA_INDEX,
                Integer.class
        );
    }

    private static SpecificationPropertyExtractor findExtractor(final PropertyMetadataSource metadataSource) {
        return findProperty(
                metadataSource,
                FIELD_NAME_PROPERTY_METADATA_SOURCE_PROPERTY_EXTRACTOR,
                SpecificationPropertyExtractor.class
        );
    }

    @SuppressWarnings("unchecked")
    private static List<SpecificationPropertyExtractor> findArgumentExtractors(
            final SubTableTitleMetadataBuilder builder
    ) {
        return findProperty(
                builder,
                FIELD_NAME_BUILDER_ARGUMENT_EXTRACTORS,
                List.class
        );
    }
}
