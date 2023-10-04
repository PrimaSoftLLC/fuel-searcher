package com.aurorasoft.fuelsearcher.service.searcher;

import com.aurorasoft.fuelsearcher.model.filter.Filter;
import com.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import com.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;
import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import com.aurorasoft.fuelsearcher.service.searcher.FilterChain.FilterChainBuilder;
import com.aurorasoft.fuelsearcher.testutil.ReflectionUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Optional.empty;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class FilterChainTest {
    private static final String FIELD_NAME_INTERIM_FILTERS = "interimFilters";
    private static final String FIELD_NAME_FINAL_FILTER = "finalFilter";

    @Test
    public void builderShouldBeCreated() {
        final FilterChainBuilder actual = FilterChain.builder();
        assertNotNull(actual);
    }

    @Test
    public void rowsShouldBeFilteredToOneRow() {
        final InterimFilter firstGivenInterimFilter = mock(InterimFilter.class);
        final InterimFilter secondGivenInterimFilter = mock(InterimFilter.class);
        final List<InterimFilter> givenInterimFilters = List.of(firstGivenInterimFilter, secondGivenInterimFilter);
        final FinalFilter givenFinalFilter = mock(FinalFilter.class);
        final FilterChain givenFilterChain = createChain(givenInterimFilters, givenFinalFilter);

        final FuelSpecification givenSpecification = mock(FuelSpecification.class);

        final XWPFTableRow firstGivenRow = mock(XWPFTableRow.class);
        final XWPFTableRow secondGivenRow = mock(XWPFTableRow.class);
        final XWPFTableRow thirdGivenRow = mock(XWPFTableRow.class);
        final List<XWPFTableRow> givenInitialRows = List.of(firstGivenRow, secondGivenRow, thirdGivenRow);

        final List<XWPFTableRow> givenFirstInterimFilterResult = List.of(firstGivenRow, secondGivenRow);
        when(firstGivenInterimFilter.filter(same(givenInitialRows), same(givenSpecification)))
                .thenReturn(givenFirstInterimFilterResult);

        final List<XWPFTableRow> givenSecondInterimFilterResult = List.of(firstGivenRow, secondGivenRow);
        when(secondGivenInterimFilter.filter(same(givenFirstInterimFilterResult), same(givenSpecification)))
                .thenReturn(givenSecondInterimFilterResult);

        when(givenFinalFilter.filter(same(givenSecondInterimFilterResult), same(givenSpecification)))
                .thenReturn(Optional.of(firstGivenRow));

        final Optional<XWPFTableRow> optionalActual = givenFilterChain.filter(givenInitialRows, givenSpecification);
        assertTrue(optionalActual.isPresent());
        final XWPFTableRow actual = optionalActual.get();
        assertSame(firstGivenRow, actual);
    }

    @Test
    public void allRowsShouldBeFiltered() {
        final InterimFilter firstGivenInterimFilter = mock(InterimFilter.class);
        final InterimFilter secondGivenInterimFilter = mock(InterimFilter.class);
        final List<InterimFilter> givenInterimFilters = List.of(firstGivenInterimFilter, secondGivenInterimFilter);
        final FinalFilter givenFinalFilter = mock(FinalFilter.class);
        final FilterChain givenFilterChain = createChain(givenInterimFilters, givenFinalFilter);

        final FuelSpecification givenSpecification = mock(FuelSpecification.class);

        final XWPFTableRow firstGivenRow = mock(XWPFTableRow.class);
        final XWPFTableRow secondGivenRow = mock(XWPFTableRow.class);
        final XWPFTableRow thirdGivenRow = mock(XWPFTableRow.class);
        final List<XWPFTableRow> givenInitialRows = List.of(firstGivenRow, secondGivenRow, thirdGivenRow);

        final List<XWPFTableRow> givenFirstInterimFilterResult = List.of(firstGivenRow, secondGivenRow);
        when(firstGivenInterimFilter.filter(same(givenInitialRows), same(givenSpecification)))
                .thenReturn(givenFirstInterimFilterResult);

        final List<XWPFTableRow> givenSecondInterimFilterResult = List.of(firstGivenRow, secondGivenRow);
        when(secondGivenInterimFilter.filter(same(givenFirstInterimFilterResult), same(givenSpecification)))
                .thenReturn(givenSecondInterimFilterResult);

        when(givenFinalFilter.filter(same(givenSecondInterimFilterResult), same(givenSpecification)))
                .thenReturn(empty());

        final Optional<XWPFTableRow> optionalActual = givenFilterChain.filter(givenInitialRows, givenSpecification);
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void filtersShouldBeFound() {
        final InterimFilter firstGivenInterimFilter = mock(InterimFilter.class);
        final InterimFilter secondGivenInterimFilter = mock(InterimFilter.class);
        final List<InterimFilter> givenInterimFilters = List.of(
                firstGivenInterimFilter,
                secondGivenInterimFilter
        );

        final FinalFilter givenFinalFilter = mock(FinalFilter.class);

        final FilterChain givenChain = createChain(givenInterimFilters, givenFinalFilter);

        final Stream<Filter<?>> actual = givenChain.findFilters();
        final List<Filter<?>> actualAsList = actual.toList();
        final List<Filter<?>> expectedAsList = List.of(
                firstGivenInterimFilter,
                secondGivenInterimFilter,
                givenFinalFilter
        );
        assertEquals(expectedAsList, actualAsList);
    }

    @Test
    public void interimFilterShouldBeAccumulatedByBuilder() {
        final FilterChainBuilder givenBuilder = FilterChain.builder();

        final InterimFilter firstGivenInterimFilter = mock(InterimFilter.class);
        final InterimFilter secondGivenInterimFilter = mock(InterimFilter.class);

        givenBuilder.interimFilter(firstGivenInterimFilter);
        givenBuilder.interimFilter(secondGivenInterimFilter);

        final List<InterimFilter> actual = findInterimFilters(givenBuilder);
        final List<InterimFilter> expected = List.of(firstGivenInterimFilter, secondGivenInterimFilter);
        assertEquals(expected, actual);
    }

    @Test
    public void finalFilterShouldBeAccumulatedToBuilder() {
        final FilterChainBuilder givenBuilder = FilterChain.builder();

        final FinalFilter givenFinalFilter = mock(FinalFilter.class);

        givenBuilder.finalFilter(givenFinalFilter);

        final FinalFilter actual = findFinalFilter(givenBuilder);
        assertSame(givenFinalFilter, actual);
    }

    @Test
    public void builderPropertiesShouldBeFound() {
        final FilterChainBuilder givenBuilder = FilterChain.builder();

        final InterimFilter firstGivenInterimFilter = mock(InterimFilter.class);
        final InterimFilter secondGivenInterimFilter = mock(InterimFilter.class);
        final FinalFilter givenFinalFilter = mock(FinalFilter.class);

        givenBuilder.interimFilter(firstGivenInterimFilter);
        givenBuilder.interimFilter(secondGivenInterimFilter);
        givenBuilder.finalFilter(givenFinalFilter);

        final Stream<Object> actual = givenBuilder.findProperties();
        final List<Object> actualAsList = actual.toList();
        final List<Object> expectedAsList = List.of(
                List.of(firstGivenInterimFilter, secondGivenInterimFilter),
                givenFinalFilter
        );
        assertEquals(expectedAsList, actualAsList);
    }

    @Test
    public void chainShouldBeBuiltAfterStateValidation() {
        final FilterChainBuilder givenBuilder = FilterChain.builder();

        final InterimFilter firstGivenInterimFilter = mock(InterimFilter.class);
        final InterimFilter secondGivenInterimFilter = mock(InterimFilter.class);
        final FinalFilter givenFinalFilter = mock(FinalFilter.class);
        givenBuilder.interimFilter(firstGivenInterimFilter);
        givenBuilder.interimFilter(secondGivenInterimFilter);
        givenBuilder.finalFilter(givenFinalFilter);

        final FilterChain actual = givenBuilder.buildAfterStateValidation();

        final List<InterimFilter> actualInterimFilters = findInterimFilters(actual);
        final List<InterimFilter> expectedInterimFilters = List.of(firstGivenInterimFilter, secondGivenInterimFilter);
        assertEquals(expectedInterimFilters, actualInterimFilters);

        final FinalFilter actualFinalFilter = findFinalFilter(actual);
        assertSame(givenFinalFilter, actualFinalFilter);
    }

    private static FilterChain createChain(final List<InterimFilter> interimFilters, final FinalFilter finalFilter) {
        return ReflectionUtil.createObject(
                FilterChain.class,
                new Class<?>[]{List.class, FinalFilter.class},
                new Object[]{interimFilters, finalFilter}
        );
    }

    @SuppressWarnings("unchecked")
    private static List<InterimFilter> findInterimFilters(final FilterChainBuilder builder) {
        return ReflectionUtil.findProperty(
                builder,
                FIELD_NAME_INTERIM_FILTERS,
                List.class
        );
    }

    private static FinalFilter findFinalFilter(final FilterChainBuilder builder) {
        return ReflectionUtil.findProperty(
                builder,
                FIELD_NAME_FINAL_FILTER,
                FinalFilter.class
        );
    }

    @SuppressWarnings("unchecked")
    private static List<InterimFilter> findInterimFilters(final FilterChain chain) {
        return ReflectionUtil.findProperty(
                chain,
                FIELD_NAME_INTERIM_FILTERS,
                List.class
        );
    }

    private static FinalFilter findFinalFilter(final FilterChain chain) {
        return ReflectionUtil.findProperty(
                chain,
                FIELD_NAME_FINAL_FILTER,
                FinalFilter.class
        );
    }
}
