package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher.CompositeSearcherBuilder;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class CompositeFuelSearcherTest {
    private static final String FIELD_NAME_SUB_TABLE_TITLE_TEMPLATE = "subTableTitleTemplate";
    private static final String FIELD_NAME_SUB_TABLE_TITLE_TEMPLATE_ARGUMENT_EXTRACTORS
            = "subTableTitleTemplateArgumentExtractors";

    @Test
    public void builderShouldBeCreated() {
        final CompositeSearcherBuilder actual = CompositeFuelSearcher.builder();
        assertNotNull(actual);
    }

    @Test
    public void subTableShouldBeFound() {
        final FuelSpecification givenSpecification = mock(FuelSpecification.class);
        final String givenSubTableTitleTemplate = "%s and %s";

        final String firstGivenProperty = "first-property";
        final SpecificationPropertyExtractor firstGivenSubTableTitleTemplateArgumentExtractor = createPropertyExtractor(
                firstGivenProperty, givenSpecification
        );

        final String secondGivenProperty = "second-property";
        final SpecificationPropertyExtractor secondGivenSubTableTitleTemplateArgumentExtractor = createPropertyExtractor(
                secondGivenProperty, givenSpecification
        );

        throw new RuntimeException();
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

    //TODO: set properties to builder and get from result searcher
    @Test
    public void searcherShouldBeBuilt() {
        final CompositeSearcherBuilder givenBuilder = CompositeFuelSearcher.builder();
        final FuelTable givenFuelTable = mock(FuelTable.class);
        final Map<String, Integer> givenFuelOffsetsByHeaders = emptyMap();
        final FilterChain givenFilterChain = mock(FilterChain.class);
        final SpecificationPropertyExtractor givenHeaderExtractor = mock(SpecificationPropertyExtractor.class);

        final CompositeFuelSearcher actual = givenBuilder.build(
                givenFuelTable,
                givenFuelOffsetsByHeaders,
                givenFilterChain,
                givenHeaderExtractor
        );
        assertNotNull(actual);
        throw new RuntimeException();
    }

    @Test
    public void additionalPropertiesShouldBeFound() {
        throw new RuntimeException();
    }

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

    private static <P> P findProperty(final CompositeSearcherBuilder builder,
                                      final String fieldName,
                                      final Class<P> propertyType)
            throws Exception {
        return findProperty(builder, fieldName, CompositeSearcherBuilder.class, propertyType);
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
}
