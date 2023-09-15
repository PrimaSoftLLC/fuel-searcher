package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import by.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;
import by.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.searcher.FilterChain.FilterChainBuilder;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher.SearcherBuilder;
import lombok.Builder;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class FuelSearcherTest {
    private static final String FIELD_NAME_FUEL_TABLE = "table";
    private static final String FIELD_NAME_HEADER_METADATA = "headerMetadata";
    private static final String FIELD_NAME_FILTER_CHAIN_BUILDER = "filterChainBuilder";

    private static final String FIELD_NAME_INTERIM_FILTERS = "interimFilters";
    private static final String FIELD_NAME_FINAL_FILTER = "finalFilter";

    private static final String FIELD_NAME_FUEL_OFFSETS_BY_HEADERS = "fuelOffsetsByHeaders";
    private static final String FIELD_NAME_FILTER_CHAIN = "filterChain";
    private static final String FIELD_NAME_HEADER_EXTRACTOR = "headerExtractor";

    @Test
    public void aliasShouldBeFound() {
        final String givenTableName = "table-name";
        final FuelTable givenTable = createTable(givenTableName);
        final FuelSearcher givenSearcher = createSearcher(givenTable);

        final String actual = givenSearcher.findAlias();
        assertEquals(givenTableName, actual);
    }

    @Test
    public void fuelTableShouldBeAccumulatedByBuilder()
            throws Exception {
        final TestSearcherBuilder givenBuilder = TestSearcherBuilder.builder().build();
        final FuelTable givenTable = mock(FuelTable.class);

        givenBuilder.table(givenTable);

        final FuelTable actual = findFuelTable(givenBuilder);
        assertSame(givenTable, actual);
    }

    @Test
    public void headerMetadataShouldBeAccumulatedByBuilder()
            throws Exception {
        final TestSearcherBuilder givenBuilder = TestSearcherBuilder.builder().build();
        final FuelHeaderMetadata givenMetadata = mock(FuelHeaderMetadata.class);

        givenBuilder.headerMetadata(givenMetadata);

        final FuelHeaderMetadata actual = findHeaderMetadata(givenBuilder);
        assertSame(givenMetadata, actual);
    }

    @Test
    public void interimFiltersShouldBeAccumulatedByBuilder()
            throws Exception {
        final TestSearcherBuilder givenBuilder = TestSearcherBuilder.builder().build();
        final InterimFilter firstGivenInterimFilter = mock(InterimFilter.class);
        final InterimFilter secondGivenInterimFilter = mock(InterimFilter.class);

        givenBuilder.interimFilter(firstGivenInterimFilter);
        givenBuilder.interimFilter(secondGivenInterimFilter);

        final FilterChainBuilder actualFilterChainBuilder = findFilterChainBuilder(givenBuilder);
        final List<InterimFilter> actual = findInterimFilters(actualFilterChainBuilder);
        final List<InterimFilter> expected = List.of(firstGivenInterimFilter, secondGivenInterimFilter);
        assertEquals(expected, actual);
    }

    @Test
    public void finalFilterShouldBeAccumulatedByBuilder()
            throws Exception {
        final TestSearcherBuilder givenBuilder = TestSearcherBuilder.builder().build();
        final FinalFilter givenFilter = mock(FinalFilter.class);

        givenBuilder.finalFilter(givenFilter);

        final FilterChainBuilder actualFilterChainBuilder = findFilterChainBuilder(givenBuilder);
        final FinalFilter actual = findFinalFilter(actualFilterChainBuilder);
        assertSame(givenFilter, actual);
    }

    @Test
    public void propertiesShouldBeFound()
            throws Exception {
        final Object firstGivenAdditionalProperty = new Object();
        final Object secondGivenAdditionalProperty = new Object();
        final TestSearcherBuilder givenBuilder = TestSearcherBuilder.builder()
                .firstAdditionalProperty(firstGivenAdditionalProperty)
                .secondAdditionalProperty(secondGivenAdditionalProperty)
                .build();

        final FuelTable givenTable = mock(FuelTable.class);
        injectFuelTable(givenBuilder, givenTable);

        final FuelHeaderMetadata givenHeaderMetadata = mock(FuelHeaderMetadata.class);
        injectHeaderMetadata(givenBuilder, givenHeaderMetadata);

        final FilterChainBuilder givenFilterChainBuilder = mock(FilterChainBuilder.class);
        injectFilterChainBuilder(givenBuilder, givenFilterChainBuilder);

        final Stream<Object> actual = givenBuilder.findProperties();
        final List<Object> actualAsList = actual.toList();
        final List<Object> expectedAsList = List.of(
                givenTable,
                givenHeaderMetadata,
                givenFilterChainBuilder,
                firstGivenAdditionalProperty,
                secondGivenAdditionalProperty
        );
        assertEquals(expectedAsList, actualAsList);
    }

    @Test
    public void searcherShouldBeBuiltAfterStateValidation()
            throws Exception {
        final TestSearcherBuilder givenBuilder = TestSearcherBuilder.builder()
                .validElements(true)
                .build();

        final FuelTable givenTable = mock(FuelTable.class);
        injectFuelTable(givenBuilder, givenTable);

        final String[] givenHeaderValues = {"first-header", "second-header", "third-header"};
        final SpecificationPropertyExtractor givenHeaderExtractor = mock(SpecificationPropertyExtractor.class);
        final FuelHeaderMetadata givenHeaderMetadata = createHeaderMetadata(givenHeaderValues, givenHeaderExtractor);
        injectHeaderMetadata(givenBuilder, givenHeaderMetadata);

        final FilterChain givenFilterChain = mock(FilterChain.class);
        final FilterChainBuilder givenFilterChainBuilder = createFilterChainBuilder(givenFilterChain);
        injectFilterChainBuilder(givenBuilder, givenFilterChainBuilder);

        final TestFuelSearcher actual = givenBuilder.buildAfterStateValidation();

        final FuelTable actualTable = findFuelTable(actual);
        assertSame(givenTable, actualTable);

        final Map<String, Integer> actualFuelOffsetsByHeaders = findFuelOffsetsByHeaders(actual);
        final Map<String, Integer> expectedFuelOffsetsByHeaders = Map.of(
                "first-header", 0,
                "second-header", 1,
                "third-header", 2
        );
        assertEquals(expectedFuelOffsetsByHeaders, actualFuelOffsetsByHeaders);

        final FilterChain actualFilterChain = findFilterChain(actual);
        assertSame(givenFilterChain, actualFilterChain);

        final SpecificationPropertyExtractor actualHeaderExtractor = findHeaderExtractor(actual);
        assertSame(givenHeaderExtractor, actualHeaderExtractor);
    }

    @Test(expected = IllegalStateException.class)
    public void searcherShouldNotBeBuiltAfterStateValidationBecauseOfFuelTableIsNotValid()
            throws Exception {
        final TestSearcherBuilder givenBuilder = TestSearcherBuilder.builder()
                .validElements(false)
                .build();

        final FuelTable givenTable = mock(FuelTable.class);
        injectFuelTable(givenBuilder, givenTable);

        givenBuilder.buildAfterStateValidation();
    }

    private static FuelTable createTable(final String name) {
        final FuelTable givenTable = mock(FuelTable.class);
        when(givenTable.getName()).thenReturn(name);
        return givenTable;
    }

    private static FuelSearcher createSearcher(final FuelTable table) {
        return new TestFuelSearcher(table, null, null, null);
    }

    private static FuelSearcher createSearcher(final XWPFTable subTable) {
        return new TestFuelSearcher(null, null, null, null, subTable);
    }

    private static FuelTable findFuelTable(final SearcherBuilder<?> builder)
            throws Exception {
        return findProperty(
                builder,
                FIELD_NAME_FUEL_TABLE,
                FuelTable.class
        );
    }

    private static FuelHeaderMetadata findHeaderMetadata(final SearcherBuilder<?> builder)
            throws Exception {
        return findProperty(
                builder,
                FIELD_NAME_HEADER_METADATA,
                FuelHeaderMetadata.class
        );
    }

    private static FilterChainBuilder findFilterChainBuilder(final SearcherBuilder<?> builder)
            throws Exception {
        return findProperty(
                builder,
                FIELD_NAME_FILTER_CHAIN_BUILDER,
                FilterChainBuilder.class
        );
    }

    private static <P> P findProperty(final SearcherBuilder<?> builder,
                                      final String fieldName,
                                      final Class<P> propertyType)
            throws Exception {
        return findProperty(
                builder,
                fieldName,
                SearcherBuilder.class,
                propertyType
        );
    }

    @SuppressWarnings("unchecked")
    private static List<InterimFilter> findInterimFilters(final FilterChainBuilder builder)
            throws Exception {
        return findProperty(
                builder,
                FIELD_NAME_INTERIM_FILTERS,
                List.class
        );
    }

    private static FinalFilter findFinalFilter(final FilterChainBuilder builder)
            throws Exception {
        return findProperty(
                builder,
                FIELD_NAME_FINAL_FILTER,
                FinalFilter.class
        );
    }

    private static <P> P findProperty(final FilterChainBuilder builder,
                                      final String fieldName,
                                      final Class<P> propertyType)
            throws Exception {
        return findProperty(
                builder,
                fieldName,
                FilterChainBuilder.class,
                propertyType
        );
    }

    private static FuelTable findFuelTable(final FuelSearcher searcher)
            throws Exception {
        return findProperty(searcher, FIELD_NAME_FUEL_TABLE, FuelTable.class);
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

    private SpecificationPropertyExtractor findHeaderExtractor(final FuelSearcher searcher)
            throws Exception {
        return findProperty(searcher, FIELD_NAME_HEADER_EXTRACTOR, SpecificationPropertyExtractor.class);
    }

    private static <P> P findProperty(final FuelSearcher searcher,
                                      final String fieldName,
                                      final Class<P> propertyType)
            throws Exception {
        return findProperty(searcher, fieldName, FuelSearcher.class, propertyType);
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

    private static void injectFuelTable(final SearcherBuilder<?> builder, final FuelTable table)
            throws Exception {
        injectProperty(builder, FIELD_NAME_FUEL_TABLE, table);
    }

    private static void injectHeaderMetadata(final SearcherBuilder<?> builder, final FuelHeaderMetadata metadata)
            throws Exception {
        injectProperty(builder, FIELD_NAME_HEADER_METADATA, metadata);
    }

    private static void injectFilterChainBuilder(final SearcherBuilder<?> builder,
                                                 final FilterChainBuilder filterChainBuilder)
            throws Exception {
        injectProperty(builder, FIELD_NAME_FILTER_CHAIN_BUILDER, filterChainBuilder);
    }

    private static void injectProperty(final SearcherBuilder<?> builder,
                                       final String fieldName,
                                       final Object propertyValue)
            throws Exception {
        final Field field = SearcherBuilder.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        try {
            field.set(builder, propertyValue);
        } finally {
            field.setAccessible(false);
        }
    }

    private static FuelHeaderMetadata createHeaderMetadata(final String[] values,
                                                           final SpecificationPropertyExtractor headerExtractor) {
        final FuelHeaderMetadata metadata = mock(FuelHeaderMetadata.class);
        when(metadata.getValues()).thenReturn(values);
        when(metadata.getHeaderExtractor()).thenReturn(headerExtractor);
        return metadata;
    }

    private static FilterChainBuilder createFilterChainBuilder(final FilterChain builtFilterChain) {
        final FilterChainBuilder builder = mock(FilterChainBuilder.class);
        when(builder.build()).thenReturn(builtFilterChain);
        return builder;
    }

    private static final class TestFuelSearcher extends FuelSearcher {
        private final XWPFTable subTable;

        public TestFuelSearcher(final FuelTable table,
                                final Map<String, Integer> fuelOffsetsByHeaders,
                                final FilterChain filterChain,
                                final SpecificationPropertyExtractor headerExtractor) {
            this(table, fuelOffsetsByHeaders, filterChain, headerExtractor, null);
        }

        public TestFuelSearcher(final FuelTable table,
                                final Map<String, Integer> fuelOffsetsByHeaders,
                                final FilterChain filterChain,
                                final SpecificationPropertyExtractor headerExtractor,
                                final XWPFTable subTable) {
            super(table, fuelOffsetsByHeaders, filterChain, headerExtractor);
            this.subTable = subTable;
        }

        @Override
        protected Optional<XWPFTable> findSubTable(final List<IBodyElement> elements,
                                                   final FuelSpecification specification) {
            return ofNullable(this.subTable);
        }
    }

    private static final class TestSearcherBuilder extends SearcherBuilder<TestFuelSearcher> {
        private final boolean validElements;
        private final String notValidElementsMessage;
        private final Object firstAdditionalProperty;
        private final Object secondAdditionalProperty;

        @Builder
        public TestSearcherBuilder(final boolean validElements,
                                   final String notValidElementsMessage,
                                   final Object firstAdditionalProperty,
                                   final Object secondAdditionalProperty) {
            this.validElements = validElements;
            this.notValidElementsMessage = notValidElementsMessage;
            this.firstAdditionalProperty = firstAdditionalProperty;
            this.secondAdditionalProperty = secondAdditionalProperty;
        }


        @Override
        protected boolean isValidElements(final List<IBodyElement> elements) {
            return this.validElements;
        }

        @Override
        protected String findNotValidElementsMessage() {
            return this.notValidElementsMessage;
        }

        @Override
        protected TestFuelSearcher build(final FuelTable fuelTable,
                                         final Map<String, Integer> fuelOffsetsByHeaders,
                                         final FilterChain filterChain,
                                         final SpecificationPropertyExtractor headerExtractor) {
            return new TestFuelSearcher(
                    fuelTable,
                    fuelOffsetsByHeaders,
                    filterChain,
                    headerExtractor
            );
        }

        @Override
        protected Stream<Object> findAdditionalProperties() {
            return Stream.of(this.firstAdditionalProperty, this.secondAdditionalProperty);
        }
    }
}
