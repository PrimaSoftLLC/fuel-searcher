package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher.CompositeSearcherBuilder;
import by.aurorasoft.fuelsearcher.util.XWPFContentUtil;
import by.aurorasoft.fuelsearcher.util.XWPFParagraphUtil;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFContentUtil.areEqualIgnoringWhitespacesAndCase;
import static by.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.extractParagraphText;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public final class CompositeFuelSearcherTest {
    private static final String FIELD_NAME_TABLE = "table";
    private static final String FIELD_NAME_FUEL_OFFSETS_BY_HEADERS = "fuelOffsetsByHeaders";
    private static final String FIELD_NAME_FILTER_CHAIN = "filterChain";
    private static final String FIELD_NAME_HEADER_EXTRACTOR = "headerExtractor";
    private static final String FIELD_NAME_SUB_TABLE_TITLE_TEMPLATE = "subTableTitleTemplate";
    private static final String FIELD_NAME_SUB_TABLE_TITLE_TEMPLATE_ARGUMENT_EXTRACTORS
            = "subTableTitleTemplateArgumentExtractors";

    @Test
    public void builderShouldBeCreated() {
        final CompositeSearcherBuilder actual = CompositeFuelSearcher.builder();
        assertNotNull(actual);
    }

    @Test
    public void subTableShouldBeFound()
            throws Exception {
        try (final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil = mockStatic(XWPFParagraphUtil.class);
             final MockedStatic<XWPFContentUtil> mockedContentComparingUtil = mockStatic(XWPFContentUtil.class)) {
            final String expectedSubTableTitleTemplate = "first-property and second-property";

            final XWPFParagraph firstGivenParagraph = createParagraph(
                    "third-property and fourth-property",
                    mockedParagraphUtil,
                    false,
                    expectedSubTableTitleTemplate,
                    mockedContentComparingUtil
            );

            final XWPFParagraph secondGivenParagraph = createParagraph(
                    "first-property and second-property",
                    mockedParagraphUtil,
                    true,
                    expectedSubTableTitleTemplate,
                    mockedContentComparingUtil
            );

            final XWPFTable firstGivenTable = mock(XWPFTable.class);
            final XWPFTable secondGivenTable = mock(XWPFTable.class);

            final List<IBodyElement> givenElements = List.of(
                    firstGivenParagraph,
                    firstGivenTable,
                    secondGivenParagraph,
                    secondGivenTable
            );

            final String givenSubTableTitleTemplate = "%s and %s";

            final FuelSpecification givenSpecification = mock(FuelSpecification.class);
            final List<SpecificationPropertyExtractor> givenSubTableTitleTemplateArgumentExtractors = List.of(
                    createPropertyExtractor("first-property", givenSpecification),
                    createPropertyExtractor("second-property", givenSpecification)
            );

            final CompositeFuelSearcher givenSearcher = createSearcher(
                    givenSubTableTitleTemplate,
                    givenSubTableTitleTemplateArgumentExtractors
            );

            final Optional<XWPFTable> optionalActual = givenSearcher.findSubTable(givenElements, givenSpecification);
            assertTrue(optionalActual.isPresent());
            final XWPFTable actual = optionalActual.get();
            assertSame(secondGivenTable, actual);
        }
    }

    @Test
    public void subTableShouldNotBeFound()
            throws Exception {
        try (final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil = mockStatic(XWPFParagraphUtil.class);
             final MockedStatic<XWPFContentUtil> mockedContentComparingUtil = mockStatic(XWPFContentUtil.class)) {
            final String expectedSubTableTitleTemplate = "first-property and second-property";

            final XWPFParagraph firstGivenParagraph = createParagraph(
                    "third-property and fourth-property",
                    mockedParagraphUtil,
                    false,
                    expectedSubTableTitleTemplate,
                    mockedContentComparingUtil
            );

            final XWPFParagraph secondGivenParagraph = createParagraph(
                    "fifth-property and sixth-property",
                    mockedParagraphUtil,
                    false,
                    expectedSubTableTitleTemplate,
                    mockedContentComparingUtil
            );

            final XWPFTable firstGivenTable = mock(XWPFTable.class);
            final XWPFTable secondGivenTable = mock(XWPFTable.class);

            final List<IBodyElement> givenElements = List.of(
                    firstGivenParagraph,
                    firstGivenTable,
                    secondGivenParagraph,
                    secondGivenTable
            );

            final String givenSubTableTitleTemplate = "%s and %s";

            final FuelSpecification givenSpecification = mock(FuelSpecification.class);
            final List<SpecificationPropertyExtractor> givenSubTableTitleTemplateArgumentExtractors = List.of(
                    createPropertyExtractor("first-property", givenSpecification),
                    createPropertyExtractor("second-property", givenSpecification)
            );

            final CompositeFuelSearcher givenSearcher = createSearcher(
                    givenSubTableTitleTemplate,
                    givenSubTableTitleTemplateArgumentExtractors
            );

            final Optional<XWPFTable> optionalActual = givenSearcher.findSubTable(givenElements, givenSpecification);
            assertTrue(optionalActual.isEmpty());
        }
    }

    @Test
    public void subTableTitleTemplateShouldBeAccumulatedByBuilder()
            throws Exception {
        final CompositeSearcherBuilder givenBuilder = CompositeFuelSearcher.builder();

        final String givenSubTableTitleTemplate = "sub-table";
        givenBuilder.subTableTitleTemplate(givenSubTableTitleTemplate);

        final String actual = findSubTableTitleTemplate(givenBuilder);
        assertEquals(givenSubTableTitleTemplate, actual);
    }

    @Test
    public void subTableTitleTemplateArgumentExtractorsShouldBeAccumulatedByBuilder()
            throws Exception {
        final CompositeSearcherBuilder givenBuilder = CompositeFuelSearcher.builder();

        final SpecificationPropertyExtractor firstGivenExtractors = mock(SpecificationPropertyExtractor.class);
        givenBuilder.subTableTitleTemplateArgumentExtractor(firstGivenExtractors);

        final SpecificationPropertyExtractor secondGivenExtractors = mock(SpecificationPropertyExtractor.class);
        givenBuilder.subTableTitleTemplateArgumentExtractor(secondGivenExtractors);

        final List<SpecificationPropertyExtractor> actualExtractors = findSubTableTitleTemplateArgumentExtractors(
                givenBuilder
        );
        final List<SpecificationPropertyExtractor> expectedExtractors = List.of(
                firstGivenExtractors, secondGivenExtractors
        );
        assertEquals(expectedExtractors, actualExtractors);
    }

    @Test
    public void elementsShouldBeValid() {
        final CompositeSearcherBuilder givenBuilder = CompositeFuelSearcher.builder();
        final List<IBodyElement> givenElements = List.of(
                mock(XWPFParagraph.class), mock(XWPFTable.class),
                mock(XWPFParagraph.class), mock(XWPFTable.class),
                mock(XWPFParagraph.class), mock(XWPFTable.class),
                mock(XWPFParagraph.class), mock(XWPFTable.class)
        );

        final boolean actual = givenBuilder.isValidElements(givenElements);
        assertTrue(actual);
    }

    @Test
    public void elementsShouldNotBeValidBecauseOfParagraphsAreNotLocatedOnEvenIndexes() {
        final CompositeSearcherBuilder givenBuilder = CompositeFuelSearcher.builder();
        final List<IBodyElement> givenElements = List.of(
                mock(XWPFParagraph.class), mock(XWPFTable.class),
                mock(XWPFParagraph.class), mock(XWPFTable.class),
                mock(XWPFParagraph.class), mock(XWPFParagraph.class),
                mock(XWPFParagraph.class), mock(XWPFTable.class)
        );

        final boolean actual = givenBuilder.isValidElements(givenElements);
        assertFalse(actual);
    }

    @Test
    public void elementsShouldNotBeValidBecauseOfTablesAreNotLocatedOnNotEvenIndexes() {
        final CompositeSearcherBuilder givenBuilder = CompositeFuelSearcher.builder();
        final List<IBodyElement> givenElements = List.of(
                mock(XWPFParagraph.class), mock(XWPFTable.class),
                mock(XWPFParagraph.class), mock(XWPFTable.class),
                mock(XWPFParagraph.class), mock(XWPFTable.class),
                mock(XWPFTable.class), mock(XWPFTable.class)
        );

        final boolean actual = givenBuilder.isValidElements(givenElements);
        assertFalse(actual);
    }

    @Test
    public void notValidElementsMessageShouldBeFound() {
        final CompositeSearcherBuilder givenBuilder = CompositeFuelSearcher.builder();

        final String actual = givenBuilder.findNotValidElementsMessage();
        final String expected = "Paragraphs should be located in not even indexes, tables should be located in even indexes";
        assertEquals(expected, actual);
    }

    @Test
    public void searcherShouldBeBuilt()
            throws Exception {
        final CompositeSearcherBuilder givenBuilder = CompositeFuelSearcher.builder();
        final FuelTable givenFuelTable = mock(FuelTable.class);
        final Map<String, Integer> givenFuelOffsetsByHeaders = emptyMap();
        final FilterChain givenFilterChain = mock(FilterChain.class);
        final SpecificationPropertyExtractor givenHeaderExtractor = mock(SpecificationPropertyExtractor.class);

        final String givenSubTableTitleTemplate = "sub-title-template";
        setSubTableTitleTemplate(givenBuilder, givenSubTableTitleTemplate);

        final List<SpecificationPropertyExtractor> givenSubTableTitleTemplateArgumentExtractors = List.of(
                mock(SpecificationPropertyExtractor.class), mock(SpecificationPropertyExtractor.class)
        );
        setSubTableTitleTemplateArgumentExtractors(givenBuilder, givenSubTableTitleTemplateArgumentExtractors);

        final CompositeFuelSearcher actual = givenBuilder.build(
                givenFuelTable,
                givenFuelOffsetsByHeaders,
                givenFilterChain,
                givenHeaderExtractor
        );

        final FuelTable actualTable = findTable(actual);
        assertSame(givenFuelTable, actualTable);

        final Map<String, Integer> actualFuelOffsetsByHeaders = findFuelOffsetsByHeaders(actual);
        assertSame(givenFuelOffsetsByHeaders, actualFuelOffsetsByHeaders);

        final FilterChain actualFilterChain = findFilterChain(actual);
        assertSame(givenFilterChain, actualFilterChain);

        final SpecificationPropertyExtractor actualHeaderExtractor = findHeaderExtractor(actual);
        assertSame(givenHeaderExtractor, actualHeaderExtractor);

        final String actualSubTableTitleTemplate = findSubTableTitleTemplate(actual);
        assertSame(givenSubTableTitleTemplate, actualSubTableTitleTemplate);

        final List<SpecificationPropertyExtractor> actualSubTableTitleTemplateArgumentExtractors
                = findSubTableTitleTemplateArgumentExtractors(actual);
        assertSame(givenSubTableTitleTemplateArgumentExtractors, actualSubTableTitleTemplateArgumentExtractors);
    }

    @Test
    public void additionalPropertiesShouldBeFound()
            throws Exception {
        final CompositeSearcherBuilder givenBuilder = CompositeFuelSearcher.builder();

        final String givenSubTableTitleTemplate = "sub-title-template";
        setSubTableTitleTemplate(givenBuilder, givenSubTableTitleTemplate);

        final List<SpecificationPropertyExtractor> givenSubTableTitleTemplateArgumentExtractors = List.of(
                mock(SpecificationPropertyExtractor.class), mock(SpecificationPropertyExtractor.class)
        );
        setSubTableTitleTemplateArgumentExtractors(givenBuilder, givenSubTableTitleTemplateArgumentExtractors);

        final Stream<Object> actual = givenBuilder.findAdditionalProperties();
        final List<Object> actualAsList = actual.toList();
        final List<Object> expectedAsList = List.of(
                givenSubTableTitleTemplate, givenSubTableTitleTemplateArgumentExtractors
        );
        assertEquals(expectedAsList, actualAsList);
    }

    @SuppressWarnings("SameParameterValue")
    private static CompositeFuelSearcher createSearcher(final String subTableTitleTemplate,
                                                        final List<SpecificationPropertyExtractor> subTableTitleTemplateArgumentExtractors)
            throws Exception {
        final Constructor<CompositeFuelSearcher> constructor = CompositeFuelSearcher.class.getDeclaredConstructor(
                FuelTable.class,
                Map.class,
                FilterChain.class,
                SpecificationPropertyExtractor.class,
                String.class,
                List.class
        );
        constructor.setAccessible(true);
        try {
            return constructor.newInstance(
                    null,
                    null,
                    null,
                    null,
                    subTableTitleTemplate,
                    subTableTitleTemplateArgumentExtractors
            );
        } finally {
            constructor.setAccessible(false);
        }
    }

    private static SpecificationPropertyExtractor createPropertyExtractor(final String property,
                                                                          final FuelSpecification specification) {
        final SpecificationPropertyExtractor extractor = mock(SpecificationPropertyExtractor.class);
        when(extractor.extract(same(specification))).thenReturn(property);
        return extractor;
    }

    private static String findSubTableTitleTemplate(final CompositeSearcherBuilder builder)
            throws Exception {
        return findProperty(builder, FIELD_NAME_SUB_TABLE_TITLE_TEMPLATE, String.class);
    }

    @SuppressWarnings("unchecked")
    private static List<SpecificationPropertyExtractor> findSubTableTitleTemplateArgumentExtractors(
            final CompositeSearcherBuilder builder)
            throws Exception {
        return findProperty(builder, FIELD_NAME_SUB_TABLE_TITLE_TEMPLATE_ARGUMENT_EXTRACTORS, List.class);
    }

    private static FuelTable findTable(final FuelSearcher searcher)
            throws Exception {
        return findProperty(searcher, FIELD_NAME_TABLE, FuelTable.class);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Integer> findFuelOffsetsByHeaders(final FuelSearcher searcher)
            throws Exception {
        return findProperty(searcher, FIELD_NAME_FUEL_OFFSETS_BY_HEADERS, Map.class);
    }

    private static FilterChain findFilterChain(final FuelSearcher searcher)
            throws Exception {
        return findProperty(searcher, FIELD_NAME_FILTER_CHAIN, FilterChain.class);
    }

    private static SpecificationPropertyExtractor findHeaderExtractor(final FuelSearcher searcher)
            throws Exception {
        return findProperty(searcher, FIELD_NAME_HEADER_EXTRACTOR, SpecificationPropertyExtractor.class);
    }

    private static String findSubTableTitleTemplate(final CompositeFuelSearcher searcher)
            throws Exception {
        return findProperty(searcher, FIELD_NAME_SUB_TABLE_TITLE_TEMPLATE, String.class);
    }

    @SuppressWarnings("unchecked")
    private static List<SpecificationPropertyExtractor> findSubTableTitleTemplateArgumentExtractors(
            final CompositeFuelSearcher searcher)
            throws Exception {
        return findProperty(searcher, FIELD_NAME_SUB_TABLE_TITLE_TEMPLATE_ARGUMENT_EXTRACTORS, List.class);
    }

    private static <P> P findProperty(final CompositeSearcherBuilder builder,
                                      final String fieldName,
                                      final Class<P> propertyType)
            throws Exception {
        return findProperty(builder, fieldName, CompositeSearcherBuilder.class, propertyType);
    }

    private static <P> P findProperty(final FuelSearcher searcher,
                                      final String fieldName,
                                      final Class<P> propertyType)
            throws Exception {
        return findProperty(searcher, fieldName, FuelSearcher.class, propertyType);
    }

    private static <P> P findProperty(final CompositeFuelSearcher searcher,
                                      final String fieldName,
                                      final Class<P> propertyType)
            throws Exception {
        return findProperty(searcher, fieldName, CompositeFuelSearcher.class, propertyType);
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
    private static void setSubTableTitleTemplate(final CompositeSearcherBuilder builder,
                                                 final String subTableTitleTemplate)
            throws Exception {
        setProperty(builder, FIELD_NAME_SUB_TABLE_TITLE_TEMPLATE, subTableTitleTemplate);
    }

    private static void setSubTableTitleTemplateArgumentExtractors(final CompositeSearcherBuilder builder,
                                                                   final List<SpecificationPropertyExtractor> subTableTitleTemplateArgumentExtractors)
            throws Exception {
        setProperty(builder, FIELD_NAME_SUB_TABLE_TITLE_TEMPLATE_ARGUMENT_EXTRACTORS, subTableTitleTemplateArgumentExtractors);
    }

    private static void setProperty(final CompositeSearcherBuilder builder,
                                    final String fieldName,
                                    final Object property)
            throws Exception {
        final Field field = CompositeSearcherBuilder.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        try {
            field.set(builder, property);
        } finally {
            field.setAccessible(false);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static XWPFParagraph createParagraph(final String content,
                                                 final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil,
                                                 final boolean matchSubTableTitleContent,
                                                 final String expectedSubTableTitleContent,
                                                 final MockedStatic<XWPFContentUtil> mockedContentComparingUtil) {
        final XWPFParagraph paragraph = mock(XWPFParagraph.class);
        mockedParagraphUtil.when(() -> extractParagraphText(same(paragraph))).thenReturn(content);
        mockedContentComparingUtil.when(
                () -> areEqualIgnoringWhitespacesAndCase(same(content), eq(expectedSubTableTitleContent))
        ).thenReturn(matchSubTableTitleContent);
        return paragraph;
    }
}
