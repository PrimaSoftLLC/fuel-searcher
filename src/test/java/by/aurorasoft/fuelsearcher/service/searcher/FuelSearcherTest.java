package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import by.aurorasoft.fuelsearcher.model.filter.Filter;
import by.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import by.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;
import by.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.searcher.FilterChain.FilterChainBuilder;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher.SearcherBuilder;
import by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil;
import by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil;
import lombok.Builder;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findProperty;
import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.setProperty;
import static by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.findFirstCellIndexByContent;
import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.extractCellDoubleValue;
import static java.lang.Double.NaN;
import static java.util.Arrays.stream;
import static java.util.Collections.emptyMap;
import static java.util.Optional.ofNullable;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public final class FuelSearcherTest {
    private static final String FIELD_NAME_TABLE = "table";
    private static final String FIELD_NAME_HEADER_METADATA = "headerMetadata";
    private static final String FIELD_NAME_FILTER_CHAIN_BUILDER = "filterChainBuilder";

    private static final String FIELD_NAME_INTERIM_FILTERS = "interimFilters";
    private static final String FIELD_NAME_FINAL_FILTER = "finalFilter";

    private static final String FIELD_NAME_FILTER_CHAIN = "filterChain";

    @Test
    public void aliasShouldBeFound() {
        final String givenTableName = "table-name";
        final FuelTable givenTable = createTable(givenTableName);
        final FuelSearcher givenSearcher = TestFuelSearcher.builder()
                .table(givenTable)
                .build();

        final String actual = givenSearcher.findAlias();
        assertSame(givenTableName, actual);
    }

    @Test
    public void fuelShouldBeFound() {
        try (final MockedStatic<XWPFTableRowFilteringUtil> mockedFilteringUtil = mockStatic(XWPFTableRowFilteringUtil.class);
             final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class)) {
            final FuelSpecification givenSpecification = mock(FuelSpecification.class);

            final FuelTable givenTable = mock(FuelTable.class);
            final XWPFTable givenSubTable = mock(XWPFTable.class);

            final SpecificationPropertyExtractor givenHeaderExtractor = mock(SpecificationPropertyExtractor.class);
            final Map<String, Integer> givenFuelOffsetsByValues = Map.of(
                    "header", 2
            );
            final FuelHeaderMetadata givenHeaderMetadata = new FuelHeaderMetadata(
                    givenHeaderExtractor,
                    givenFuelOffsetsByValues) {
            };

            final FilterChain givenFilterChain = mock(FilterChain.class);
            final FuelSearcher givenSearcher = TestFuelSearcher.builder()
                    .table(givenTable)
                    .subTable(givenSubTable)
                    .headerMetadata(givenHeaderMetadata)
                    .filterChain(givenFilterChain)
                    .build();

            final XWPFTableRow firstGivenRow = mock(XWPFTableRow.class);
            final XWPFTableRow secondGivenRow = mock(XWPFTableRow.class);
            final XWPFTableRow thirdGivenRow = mock(XWPFTableRow.class);
            final List<XWPFTableRow> givenRows = List.of(firstGivenRow, secondGivenRow, thirdGivenRow);
            when(givenSubTable.getRows()).thenReturn(givenRows);

            when(givenFilterChain.filter(same(givenRows), same(givenSpecification)))
                    .thenReturn(Optional.of(secondGivenRow));

            final String givenHeader = "header";
            when(givenHeaderExtractor.extract(same(givenSpecification))).thenReturn(givenHeader);

            final int givenFuelHeaderCellIndex = 3;
            mockedFilteringUtil.when(
                    () -> findFirstCellIndexByContent(same(secondGivenRow), same(givenHeader))
            ).thenReturn(OptionalInt.of(givenFuelHeaderCellIndex));

            final int expectedGenerationNormCellIndex = 5;
            final double givenGenerationNorm = 5.5;
            mockedRowUtil.when(
                    () -> extractCellDoubleValue(same(secondGivenRow), eq(expectedGenerationNormCellIndex))
            ).thenReturn(givenGenerationNorm);

            final int expectedConsumptionCellIndex = 6;
            final double givenConsumption = 6.6;
            mockedRowUtil.when(
                    () -> extractCellDoubleValue(same(secondGivenRow), eq(expectedConsumptionCellIndex))
            ).thenReturn(givenConsumption);

            final Optional<Fuel> optionalActual = givenSearcher.find(givenSpecification);
            assertTrue(optionalActual.isPresent());
            final Fuel actual = optionalActual.get();
            final Fuel expected = new Fuel(givenGenerationNorm, givenConsumption);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void fuelShouldNotBeFoundBecauseOfAllRowsWereFiltered() {
        final FuelSpecification givenSpecification = mock(FuelSpecification.class);

        final FuelTable givenTable = mock(FuelTable.class);
        final XWPFTable givenSubTable = mock(XWPFTable.class);
        final FilterChain givenFilterChain = mock(FilterChain.class);
        final FuelSearcher givenSearcher = TestFuelSearcher.builder()
                .table(givenTable)
                .subTable(givenSubTable)
                .filterChain(givenFilterChain)
                .build();

        final XWPFTableRow firstGivenRow = mock(XWPFTableRow.class);
        final XWPFTableRow secondGivenRow = mock(XWPFTableRow.class);
        final XWPFTableRow thirdGivenRow = mock(XWPFTableRow.class);
        final List<XWPFTableRow> givenRows = List.of(firstGivenRow, secondGivenRow, thirdGivenRow);
        when(givenSubTable.getRows()).thenReturn(givenRows);

        when(givenFilterChain.filter(same(givenRows), same(givenSpecification))).thenReturn(Optional.empty());

        final Optional<Fuel> optionalActual = givenSearcher.find(givenSpecification);
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void fuelShouldNotBeFoundBecauseOfCellWasNotFoundByFuelHeader() {
        try (final MockedStatic<XWPFTableRowFilteringUtil> mockedFilteringUtil = mockStatic(XWPFTableRowFilteringUtil.class)) {
            final FuelSpecification givenSpecification = mock(FuelSpecification.class);

            final FuelTable givenTable = mock(FuelTable.class);
            final XWPFTable givenSubTable = mock(XWPFTable.class);

            final SpecificationPropertyExtractor givenHeaderExtractor = mock(SpecificationPropertyExtractor.class);
            final Map<String, Integer> givenFuelOffsetsByValues = emptyMap();
            final FuelHeaderMetadata givenHeaderMetadata = new FuelHeaderMetadata(
                    givenHeaderExtractor,
                    givenFuelOffsetsByValues) {
            };

            final FilterChain givenFilterChain = mock(FilterChain.class);
            final FuelSearcher givenSearcher = TestFuelSearcher.builder()
                    .table(givenTable)
                    .subTable(givenSubTable)
                    .headerMetadata(givenHeaderMetadata)
                    .filterChain(givenFilterChain)
                    .build();

            final XWPFTableRow firstGivenRow = mock(XWPFTableRow.class);
            final XWPFTableRow secondGivenRow = mock(XWPFTableRow.class);
            final XWPFTableRow thirdGivenRow = mock(XWPFTableRow.class);
            final List<XWPFTableRow> givenRows = List.of(firstGivenRow, secondGivenRow, thirdGivenRow);
            when(givenSubTable.getRows()).thenReturn(givenRows);

            when(givenFilterChain.filter(same(givenRows), same(givenSpecification)))
                    .thenReturn(Optional.of(secondGivenRow));

            final String givenHeader = "header";
            when(givenHeaderExtractor.extract(same(givenSpecification))).thenReturn(givenHeader);

            mockedFilteringUtil.when(
                    () -> findFirstCellIndexByContent(same(secondGivenRow), same(givenHeader))
            ).thenReturn(OptionalInt.empty());

            final Optional<Fuel> optionalActual = givenSearcher.find(givenSpecification);
            assertTrue(optionalActual.isEmpty());
        }
    }

    @Test(expected = Exception.class)
    public void fuelShouldNotBeFoundBecauseOfOffsetDoesNotExist() {
        try (final MockedStatic<XWPFTableRowFilteringUtil> mockedFilteringUtil = mockStatic(XWPFTableRowFilteringUtil.class)) {
            final FuelSpecification givenSpecification = mock(FuelSpecification.class);

            final FuelTable givenTable = mock(FuelTable.class);
            final XWPFTable givenSubTable = mock(XWPFTable.class);

            final SpecificationPropertyExtractor givenHeaderExtractor = mock(SpecificationPropertyExtractor.class);
            final Map<String, Integer> givenFuelOffsetsByValues = Map.of("header", 2);
            final FuelHeaderMetadata givenHeaderMetadata = new FuelHeaderMetadata(
                    givenHeaderExtractor,
                    givenFuelOffsetsByValues) {
            };

            final FilterChain givenFilterChain = mock(FilterChain.class);
            final FuelSearcher givenSearcher = TestFuelSearcher.builder()
                    .table(givenTable)
                    .subTable(givenSubTable)
                    .headerMetadata(givenHeaderMetadata)
                    .filterChain(givenFilterChain)
                    .build();

            final XWPFTableRow firstGivenRow = mock(XWPFTableRow.class);
            final XWPFTableRow secondGivenRow = mock(XWPFTableRow.class);
            final XWPFTableRow thirdGivenRow = mock(XWPFTableRow.class);
            final List<XWPFTableRow> givenRows = List.of(firstGivenRow, secondGivenRow, thirdGivenRow);
            when(givenSubTable.getRows()).thenReturn(givenRows);

            when(givenFilterChain.filter(same(givenRows), same(givenSpecification))).thenReturn(Optional.of(secondGivenRow));

            final String givenHeader = "header2";
            when(givenHeaderExtractor.extract(same(givenSpecification))).thenReturn(givenHeader);

            final int givenFuelHeaderCellIndex = 3;
            mockedFilteringUtil.when(
                    () -> findFirstCellIndexByContent(same(secondGivenRow), same(givenHeader))
            ).thenReturn(OptionalInt.of(givenFuelHeaderCellIndex));

            givenSearcher.find(givenSpecification);
        }
    }

    @Test
    public void fuelShouldNotBeFoundBecauseOfNotDefinedFuelWasFound() {
        try (final MockedStatic<XWPFTableRowFilteringUtil> mockedFilteringUtil = mockStatic(XWPFTableRowFilteringUtil.class);
             final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class)) {
            final FuelSpecification givenSpecification = mock(FuelSpecification.class);

            final FuelTable givenTable = mock(FuelTable.class);
            final XWPFTable givenSubTable = mock(XWPFTable.class);

            final SpecificationPropertyExtractor givenHeaderExtractor = mock(SpecificationPropertyExtractor.class);
            final Map<String, Integer> givenFuelOffsetsByValues = Map.of("header", 2);
            final FuelHeaderMetadata givenHeaderMetadata = new FuelHeaderMetadata(
                    givenHeaderExtractor,
                    givenFuelOffsetsByValues) {
            };

            final FilterChain givenFilterChain = mock(FilterChain.class);
            final FuelSearcher givenSearcher = TestFuelSearcher.builder()
                    .table(givenTable)
                    .subTable(givenSubTable)
                    .headerMetadata(givenHeaderMetadata)
                    .filterChain(givenFilterChain)
                    .build();

            final XWPFTableRow firstGivenRow = mock(XWPFTableRow.class);
            final XWPFTableRow secondGivenRow = mock(XWPFTableRow.class);
            final XWPFTableRow thirdGivenRow = mock(XWPFTableRow.class);
            final List<XWPFTableRow> givenRows = List.of(firstGivenRow, secondGivenRow, thirdGivenRow);
            when(givenSubTable.getRows()).thenReturn(givenRows);

            when(givenFilterChain.filter(same(givenRows), same(givenSpecification)))
                    .thenReturn(Optional.of(secondGivenRow));

            final String givenHeader = "header";
            when(givenHeaderExtractor.extract(same(givenSpecification))).thenReturn(givenHeader);

            final int givenFuelHeaderCellIndex = 3;
            mockedFilteringUtil.when(
                    () -> findFirstCellIndexByContent(same(secondGivenRow), same(givenHeader))
            ).thenReturn(OptionalInt.of(givenFuelHeaderCellIndex));

            final int expectedGenerationNormCellIndex = 5;
            mockedRowUtil.when(
                    () -> extractCellDoubleValue(same(secondGivenRow), eq(expectedGenerationNormCellIndex))
            ).thenReturn(NaN);

            final int expectedConsumptionCellIndex = 6;
            mockedRowUtil.when(
                    () -> extractCellDoubleValue(same(secondGivenRow), eq(expectedConsumptionCellIndex))
            ).thenReturn(NaN);

            final Optional<Fuel> optionalActual = givenSearcher.find(givenSpecification);
            assertTrue(optionalActual.isEmpty());
        }
    }

    @Test
    public void fuelShouldNotBeFoundBecauseOfSubTableWasNotFound() {
        final FuelTable givenTable = mock(FuelTable.class);
        final FuelSearcher givenSearcher = TestFuelSearcher.builder()
                .table(givenTable)
                .build();
        final FuelSpecification givenSpecification = mock(FuelSpecification.class);

        final Optional<Fuel> optionalActual = givenSearcher.find(givenSpecification);
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void tableNameShouldBeFound() {
        final String givenTableName = "table-name";
        final FuelTable givenTable = createTable(givenTableName);
        final FuelSearcher givenSearcher = TestFuelSearcher.builder()
                .table(givenTable)
                .build();

        final String actual = givenSearcher.findTableName();
        assertSame(givenTableName, actual);
    }

    @Test
    public void usedPropertyMetadataSourcesShouldBeFound() {
        final Filter<?> firstGivenFilter = mock(Filter.class);
        final Filter<?> secondGivenFilter = mock(Filter.class);
        final FilterChain givenFilterChain = createFilterChain(firstGivenFilter, secondGivenFilter);

        final FuelHeaderMetadata givenHeaderMetadata = mock(FuelHeaderMetadata.class);

        final PropertyMetadataSource firstGivenAdditionalPropertyMetadataSource = mock(PropertyMetadataSource.class);
        final PropertyMetadataSource secondGivenAdditionalPropertyMetadataSource = mock(PropertyMetadataSource.class);
        final Stream<PropertyMetadataSource> givenAdditionalPropertyMetadataSources = Stream.of(
                firstGivenAdditionalPropertyMetadataSource,
                secondGivenAdditionalPropertyMetadataSource
        );

        final FuelSearcher givenSearcher = TestFuelSearcher.builder()
                .filterChain(givenFilterChain)
                .headerMetadata(givenHeaderMetadata)
                .additionalPropertyMetadataSources(givenAdditionalPropertyMetadataSources)
                .build();

        final Stream<PropertyMetadataSource> actual = givenSearcher.findUsedPropertyMetadataSources();
        final List<PropertyMetadataSource> actualAsList = actual.toList();
        final List<PropertyMetadataSource> expectedAsList = List.of(
                firstGivenFilter,
                secondGivenFilter,
                givenHeaderMetadata,
                firstGivenAdditionalPropertyMetadataSource,
                secondGivenAdditionalPropertyMetadataSource
        );
        assertEquals(expectedAsList, actualAsList);
    }

    @Test
    public void usedPropertyExtractorsShouldBeFound() {
        final SpecificationPropertyExtractor firstGivenPropertyExtractor = mock(SpecificationPropertyExtractor.class);
        final Filter<?> firstGivenFilter = createFilter(firstGivenPropertyExtractor);

        final SpecificationPropertyExtractor secondGivenPropertyExtractor = mock(SpecificationPropertyExtractor.class);
        final Filter<?> secondGivenFilter = createFilter(secondGivenPropertyExtractor);

        final FilterChain givenFilterChain = createFilterChain(firstGivenFilter, secondGivenFilter);

        final SpecificationPropertyExtractor thirdGivenPropertyExtractor = mock(SpecificationPropertyExtractor.class);
        final FuelHeaderMetadata givenHeaderMetadata = createHeaderMetadata(thirdGivenPropertyExtractor);

        final SpecificationPropertyExtractor fourthGivenPropertyExtractor = mock(SpecificationPropertyExtractor.class);
        final PropertyMetadataSource firstGivenAdditionalPropertyMetadataSource = createMetadataSource(
                fourthGivenPropertyExtractor
        );

        final SpecificationPropertyExtractor fifthGivenPropertyExtractor = mock(SpecificationPropertyExtractor.class);
        final PropertyMetadataSource secondGivenAdditionalPropertyMetadataSource = createMetadataSource(
                fifthGivenPropertyExtractor
        );

        final Stream<PropertyMetadataSource> givenAdditionalPropertyMetadataSources = Stream.of(
                firstGivenAdditionalPropertyMetadataSource,
                secondGivenAdditionalPropertyMetadataSource
        );

        final FuelSearcher givenSearcher = TestFuelSearcher.builder()
                .filterChain(givenFilterChain)
                .headerMetadata(givenHeaderMetadata)
                .additionalPropertyMetadataSources(givenAdditionalPropertyMetadataSources)
                .build();

        final List<SpecificationPropertyExtractor> actual = givenSearcher.findUsedPropertyExtractors();
        final List<SpecificationPropertyExtractor> expected = List.of(
                firstGivenPropertyExtractor,
                secondGivenPropertyExtractor,
                thirdGivenPropertyExtractor,
                fourthGivenPropertyExtractor,
                fifthGivenPropertyExtractor
        );
        assertEquals(expected, actual);
    }

    @Test
    public void fuelTableShouldBeAccumulatedByBuilder() {
        final TestSearcherBuilder givenBuilder = TestSearcherBuilder.builder().build();
        final FuelTable givenTable = mock(FuelTable.class);

        givenBuilder.table(givenTable);

        final FuelTable actual = findTable(givenBuilder);
        assertSame(givenTable, actual);
    }

    @Test
    public void headerMetadataShouldBeAccumulatedByBuilder() {
        final TestSearcherBuilder givenBuilder = TestSearcherBuilder.builder().build();
        final FuelHeaderMetadata givenMetadata = mock(FuelHeaderMetadata.class);

        givenBuilder.headerMetadata(givenMetadata);

        final FuelHeaderMetadata actual = findHeaderMetadata(givenBuilder);
        assertSame(givenMetadata, actual);
    }

    @Test
    public void interimFiltersShouldBeAccumulatedByBuilder() {
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
    public void finalFilterShouldBeAccumulatedByBuilder() {
        final TestSearcherBuilder givenBuilder = TestSearcherBuilder.builder().build();
        final FinalFilter givenFilter = mock(FinalFilter.class);

        givenBuilder.finalFilter(givenFilter);

        final FilterChainBuilder actualFilterChainBuilder = findFilterChainBuilder(givenBuilder);
        final FinalFilter actual = findFinalFilter(actualFilterChainBuilder);
        assertSame(givenFilter, actual);
    }

    @Test
    public void propertiesShouldBeFound() {
        final Object firstGivenAdditionalProperty = new Object();
        final Object secondGivenAdditionalProperty = new Object();
        final TestSearcherBuilder givenBuilder = TestSearcherBuilder.builder()
                .firstAdditionalProperty(firstGivenAdditionalProperty)
                .secondAdditionalProperty(secondGivenAdditionalProperty)
                .build();

        final FuelTable givenTable = mock(FuelTable.class);
        setFuelTable(givenBuilder, givenTable);

        final FuelHeaderMetadata givenHeaderMetadata = mock(FuelHeaderMetadata.class);
        setHeaderMetadata(givenBuilder, givenHeaderMetadata);

        final FilterChainBuilder givenFilterChainBuilder = mock(FilterChainBuilder.class);
        setFilterChainBuilder(givenBuilder, givenFilterChainBuilder);

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

//    @Test
//    public void searcherShouldBeBuiltAfterStateValidation() {
//        final TestSearcherBuilder givenBuilder = TestSearcherBuilder.builder()
//                .validElements(true)
//                .build();
//
//        final FuelTable givenTable = mock(FuelTable.class);
//        setFuelTable(givenBuilder, givenTable);
//
//        final String[] givenHeaderValues = {"first-header", "second-header", "third-header"};
//        final SpecificationPropertyExtractor givenHeaderExtractor = mock(SpecificationPropertyExtractor.class);
//        final FuelHeaderMetadata givenHeaderMetadata = createHeaderMetadata(givenHeaderValues, givenHeaderExtractor);
//        setHeaderMetadata(givenBuilder, givenHeaderMetadata);
//
//        final FilterChain givenFilterChain = mock(FilterChain.class);
//        final FilterChainBuilder givenFilterChainBuilder = createFilterChainBuilder(givenFilterChain);
//        setFilterChainBuilder(givenBuilder, givenFilterChainBuilder);
//
//        final TestFuelSearcher actual = givenBuilder.buildAfterStateValidation();
//
//        final FuelTable actualTable = findFuelTable(actual);
//        assertSame(givenTable, actualTable);
//
//        final Map<String, Integer> actualFuelOffsetsByHeaders = findFuelOffsetsByHeaders(actual);
//        final Map<String, Integer> expectedFuelOffsetsByHeaders = Map.of(
//                "first-header", 0,
//                "second-header", 1,
//                "third-header", 2
//        );
//        assertEquals(expectedFuelOffsetsByHeaders, actualFuelOffsetsByHeaders);
//
//        final FilterChain actualFilterChain = findFilterChain(actual);
//        assertSame(givenFilterChain, actualFilterChain);
//
//        final SpecificationPropertyExtractor actualHeaderExtractor = findHeaderExtractor(actual);
//        assertSame(givenHeaderExtractor, actualHeaderExtractor);
//    }
//
//    @Test
//    public void searcherShouldNotBeBuiltAfterStateValidationBecauseOfFuelTableIsNotValid() {
//        final String givenNotValidElementsMessage = "message";
//        final TestSearcherBuilder givenBuilder = TestSearcherBuilder.builder()
//                .validElements(false)
//                .notValidElementsMessage(givenNotValidElementsMessage)
//                .build();
//
//        final FuelTable givenTable = mock(FuelTable.class);
//        setFuelTable(givenBuilder, givenTable);
//
//        boolean exceptionArisen = false;
//        try {
//            givenBuilder.buildAfterStateValidation();
//        } catch (final IllegalStateException exception) {
//            assertEquals(givenNotValidElementsMessage, exception.getMessage());
//            exceptionArisen = true;
//        }
//        assertTrue(exceptionArisen);
//    }

    @SuppressWarnings("SameParameterValue")
    private static FuelTable createTable(final String name) {
        final FuelTable givenTable = mock(FuelTable.class);
        when(givenTable.name()).thenReturn(name);
        return givenTable;
    }

    private static FuelTable findTable(final SearcherBuilder<?> builder) {
        return findProperty(
                builder,
                FIELD_NAME_TABLE,
                FuelTable.class
        );
    }

    private static FuelHeaderMetadata findHeaderMetadata(final SearcherBuilder<?> builder) {
        return findProperty(
                builder,
                FIELD_NAME_HEADER_METADATA,
                FuelHeaderMetadata.class
        );
    }

    private static FilterChainBuilder findFilterChainBuilder(final SearcherBuilder<?> builder) {
        return findProperty(
                builder,
                FIELD_NAME_FILTER_CHAIN_BUILDER,
                FilterChainBuilder.class
        );
    }

    @SuppressWarnings("unchecked")
    private static List<InterimFilter> findInterimFilters(final FilterChainBuilder builder) {
        return findProperty(
                builder,
                FIELD_NAME_INTERIM_FILTERS,
                List.class
        );
    }

    private static FinalFilter findFinalFilter(final FilterChainBuilder builder) {
        return findProperty(
                builder,
                FIELD_NAME_FINAL_FILTER,
                FinalFilter.class
        );
    }

    private static FuelTable findTable(final FuelSearcher searcher) {
        return findProperty(
                searcher,
                FIELD_NAME_TABLE,
                FuelTable.class
        );
    }

    private static FilterChain findFilterChain(final FuelSearcher searcher) {
        return findProperty(
                searcher,
                FIELD_NAME_FILTER_CHAIN,
                FilterChain.class
        );
    }

    private static void setFuelTable(final SearcherBuilder<?> builder, final FuelTable table) {
        setProperty(
                builder,
                table,
                FIELD_NAME_TABLE
        );
    }

    private static void setHeaderMetadata(final SearcherBuilder<?> builder, final FuelHeaderMetadata metadata) {
        setProperty(
                builder,
                metadata,
                FIELD_NAME_HEADER_METADATA
        );
    }

    private static void setFilterChainBuilder(final SearcherBuilder<?> builder,
                                              final FilterChainBuilder filterChainBuilder) {
        setProperty(
                builder,
                filterChainBuilder,
                FIELD_NAME_FILTER_CHAIN_BUILDER
        );
    }

    private static FilterChain createFilterChain(final Filter<?>... filters) {
        final FilterChain filterChain = mock(FilterChain.class);
        when(filterChain.findFilters()).thenReturn(stream(filters));
        return filterChain;
    }

    private static Filter<?> createFilter(final SpecificationPropertyExtractor propertyExtractor) {
        return createMetadataSource(propertyExtractor, Filter.class);
    }

    private static FuelHeaderMetadata createHeaderMetadata(final SpecificationPropertyExtractor propertyExtractor) {
        return createMetadataSource(propertyExtractor, FuelHeaderMetadata.class);
    }

    private static PropertyMetadataSource createMetadataSource(final SpecificationPropertyExtractor propertyExtractor) {
        return createMetadataSource(propertyExtractor, PropertyMetadataSource.class);
    }

    private static <S extends PropertyMetadataSource> S createMetadataSource(
            final SpecificationPropertyExtractor propertyExtractor,
            final Class<S> sourceType
    ) {
        final S source = mock(sourceType);
        when(source.getPropertyExtractor()).thenReturn(propertyExtractor);
        return source;
    }

    private static FuelHeaderMetadata createHeaderMetadata(final String[] headerValues,
                                                           final SpecificationPropertyExtractor propertyExtractor) {
        final FuelHeaderMetadata metadata = createHeaderMetadata(propertyExtractor);
        when(metadata.findHeaderValues()).thenReturn(headerValues);
        return metadata;
    }

    private static FilterChainBuilder createFilterChainBuilder(final FilterChain builtFilterChain) {
        final FilterChainBuilder builder = mock(FilterChainBuilder.class);
        when(builder.build()).thenReturn(builtFilterChain);
        return builder;
    }

    private static final class TestFuelSearcher extends FuelSearcher {
        private final XWPFTable subTable;
        private final Stream<PropertyMetadataSource> propertyMetadataSources;

        @Builder
        public TestFuelSearcher(final FuelTable table,
                                final FuelHeaderMetadata headerMetadata,
                                final FilterChain filterChain,
                                final XWPFTable subTable,
                                final Stream<PropertyMetadataSource> additionalPropertyMetadataSources) {
            super(table, headerMetadata, filterChain);
            this.subTable = subTable;
            this.propertyMetadataSources = additionalPropertyMetadataSources;
        }

        @Override
        protected Optional<XWPFTable> findSubTable(final List<IBodyElement> elements,
                                                   final FuelSpecification specification) {
            return ofNullable(this.subTable);
        }

        @Override
        protected Stream<? extends PropertyMetadataSource> findAdditionalPropertyMetadataSources() {
            return this.propertyMetadataSources;
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
        protected TestFuelSearcher build(final FuelTable table,
                                         final FuelHeaderMetadata headerMetadata,
                                         final FilterChain filterChain) {
            return TestFuelSearcher.builder()
                    .table(table)
                    .headerMetadata(headerMetadata)
                    .filterChain(filterChain)
                    .build();
        }

        @Override
        protected Stream<Object> findAdditionalProperties() {
            return Stream.of(this.firstAdditionalProperty, this.secondAdditionalProperty);
        }
    }
}
