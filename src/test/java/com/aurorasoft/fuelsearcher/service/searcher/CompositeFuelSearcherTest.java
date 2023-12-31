package com.aurorasoft.fuelsearcher.service.searcher;

import com.aurorasoft.fuelsearcher.model.FuelTable;
import com.aurorasoft.fuelsearcher.model.SubTableTitleMetadata;
import com.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import com.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher.CompositeSearcherBuilder;
import com.aurorasoft.fuelsearcher.util.XWPFContentUtil;
import com.aurorasoft.fuelsearcher.util.XWPFParagraphUtil;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.aurorasoft.fuelsearcher.testutil.ReflectionUtil.*;
import static com.aurorasoft.fuelsearcher.util.XWPFContentUtil.areEqualConsideringOnlyLettersAndDigits;
import static com.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.extractParagraphText;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public final class CompositeFuelSearcherTest {
    private static final String FIELD_NAME_TABLE = "table";
    private static final String FIELD_NAME_HEADER_METADATA = "headerMetadata";
    private static final String FIELD_NAME_FILTER_CHAIN = "filterChain";
    private static final String FIELD_NAME_SUB_TABLE_TITLE_METADATA = "subTableTitleMetadata";

    @Test
    public void builderShouldBeCreated() {
        final CompositeSearcherBuilder actual = CompositeFuelSearcher.builder();
        assertNotNull(actual);
    }

    @Test
    public void subTableShouldBeFound() {
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
            final SubTableTitleMetadata givenSubTableTitleMetadata = createSubTableTitleMetadata(
                    givenSubTableTitleTemplate,
                    givenSubTableTitleTemplateArgumentExtractors
            );
            final CompositeFuelSearcher givenSearcher = createSearcher(givenSubTableTitleMetadata);

            final Optional<XWPFTable> optionalActual = givenSearcher.findSubTable(givenElements, givenSpecification);
            assertTrue(optionalActual.isPresent());
            final XWPFTable actual = optionalActual.get();
            assertSame(secondGivenTable, actual);
        }
    }

    @Test
    public void subTableShouldNotBeFound() {
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
            final SubTableTitleMetadata givenSubTableTitleMetadata = createSubTableTitleMetadata(
                    givenSubTableTitleTemplate,
                    givenSubTableTitleTemplateArgumentExtractors
            );
            final CompositeFuelSearcher givenSearcher = createSearcher(givenSubTableTitleMetadata);

            final Optional<XWPFTable> optionalActual = givenSearcher.findSubTable(givenElements, givenSpecification);
            assertTrue(optionalActual.isEmpty());
        }
    }

    @Test
    public void subTableTitleMetadataShouldBeAccumulatedByBuilder() {
        final CompositeSearcherBuilder givenBuilder = CompositeFuelSearcher.builder();
        final SubTableTitleMetadata givenSubTableTitleMetadata = mock(SubTableTitleMetadata.class);

        givenBuilder.subTableTitleMetadata(givenSubTableTitleMetadata);

        final SubTableTitleMetadata actual = findSubTableTitleMetadata(givenBuilder);
        assertSame(givenSubTableTitleMetadata, actual);
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
        final String expected = "Paragraphs should be located in not even indexes, "
                + "tables should be located in even indexes";
        assertEquals(expected, actual);
    }

    @Test
    public void searcherShouldBeBuilt() {
        final CompositeSearcherBuilder givenBuilder = CompositeFuelSearcher.builder();
        final FuelTable givenFuelTable = mock(FuelTable.class);
        final FuelHeaderMetadata givenHeaderMetadata = mock(FuelHeaderMetadata.class);
        final FilterChain givenFilterChain = Mockito.mock(FilterChain.class);

        final SubTableTitleMetadata givenSubTableTitleMetadata = mock(SubTableTitleMetadata.class);
        setSubTableTitleMetadata(givenBuilder, givenSubTableTitleMetadata);

        final CompositeFuelSearcher actual = givenBuilder.build(
                givenFuelTable,
                givenHeaderMetadata,
                givenFilterChain
        );

        final FuelTable actualTable = findTable(actual);
        assertSame(givenFuelTable, actualTable);

        final FuelHeaderMetadata actualHeaderMetadata = findHeaderMetadata(actual);
        assertSame(givenHeaderMetadata, actualHeaderMetadata);

        final FilterChain actualFilterChain = findFilterChain(actual);
        assertSame(givenFilterChain, actualFilterChain);

        final SubTableTitleMetadata actualSubTableTitleMetadata = findSubTableTitleMetadata(actual);
        assertSame(givenSubTableTitleMetadata, actualSubTableTitleMetadata);
    }

    @Test
    public void additionalPropertiesShouldBeFound() {
        final CompositeSearcherBuilder givenBuilder = CompositeFuelSearcher.builder();

        final SubTableTitleMetadata givenSubTableTitleMetadata = mock(SubTableTitleMetadata.class);
        setSubTableTitleMetadata(givenBuilder, givenSubTableTitleMetadata);

        final Stream<Object> actual = givenBuilder.findAdditionalProperties();
        final List<Object> actualAsList = actual.toList();
        final List<Object> expectedAsList = List.of(givenSubTableTitleMetadata);
        assertEquals(expectedAsList, actualAsList);
    }

    private static CompositeFuelSearcher createSearcher(final SubTableTitleMetadata subTableTitleMetadata) {
        return createObject(
                CompositeFuelSearcher.class,
                new Class<?>[]{
                        FuelTable.class,
                        FuelHeaderMetadata.class,
                        FilterChain.class,
                        SubTableTitleMetadata.class
                },
                new Object[]{
                        null,
                        null,
                        null,
                        subTableTitleMetadata
                }
        );
    }

    @SuppressWarnings("SameParameterValue")
    private static SubTableTitleMetadata createSubTableTitleMetadata(final String template,
                                                                     final List<SpecificationPropertyExtractor> argumentExtractors) {
        return createObject(
                SubTableTitleMetadata.class,
                new Class<?>[]{String.class, String.class, List.class},
                new Object[]{template, null, argumentExtractors}
        );
    }

    private static SpecificationPropertyExtractor createPropertyExtractor(final String property,
                                                                          final FuelSpecification specification) {
        final SpecificationPropertyExtractor extractor = mock(SpecificationPropertyExtractor.class);
        when(extractor.extract(same(specification))).thenReturn(property);
        return extractor;
    }

    private static SubTableTitleMetadata findSubTableTitleMetadata(final CompositeSearcherBuilder builder) {
        return findProperty(
                builder,
                FIELD_NAME_SUB_TABLE_TITLE_METADATA,
                SubTableTitleMetadata.class
        );
    }

    private static FuelTable findTable(final FuelSearcher searcher) {
        return findProperty(
                searcher,
                FIELD_NAME_TABLE,
                FuelTable.class
        );
    }

    private static FuelHeaderMetadata findHeaderMetadata(final FuelSearcher searcher) {
        return findProperty(
                searcher,
                FIELD_NAME_HEADER_METADATA,
                FuelHeaderMetadata.class
        );
    }

    private static FilterChain findFilterChain(final FuelSearcher searcher) {
        return findProperty(
                searcher,
                FIELD_NAME_FILTER_CHAIN,
                FilterChain.class
        );
    }

    private static SubTableTitleMetadata findSubTableTitleMetadata(final CompositeFuelSearcher searcher) {
        return findProperty(
                searcher,
                FIELD_NAME_SUB_TABLE_TITLE_METADATA,
                SubTableTitleMetadata.class
        );
    }

    private static void setSubTableTitleMetadata(final CompositeSearcherBuilder builder,
                                                 final SubTableTitleMetadata metadata) {
        setProperty(
                builder,
                metadata,
                FIELD_NAME_SUB_TABLE_TITLE_METADATA
        );
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
                () -> areEqualConsideringOnlyLettersAndDigits(same(content), eq(expectedSubTableTitleContent))
        ).thenReturn(matchSubTableTitleContent);
        return paragraph;
    }
}
