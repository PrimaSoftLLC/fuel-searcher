package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import by.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.service.searcher.FilterChain.FilterChainBuilder;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
    public void rowsShouldBeFilteredToOneRow()
            throws Exception {
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
    public void allRowsShouldBeFiltered()
            throws Exception {
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
    public void interimFilterShouldBeAccumulatedByBuilder()
            throws Exception {
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
    public void finalFilterShouldBeAccumulatedToBuilder()
            throws Exception {
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
    public void chainShouldBeBuiltAfterStateValidation()
            throws Exception {
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

    private static FilterChain createChain(final List<InterimFilter> interimFilters, final FinalFilter finalFilter)
            throws Exception {
        final Constructor<FilterChain> constructor = FilterChain.class.getDeclaredConstructor(
                List.class, FinalFilter.class
        );
        constructor.setAccessible(true);
        try {
            return constructor.newInstance(interimFilters, finalFilter);
        } finally {
            constructor.setAccessible(false);
        }
    }

    @SuppressWarnings("unchecked")
    private static List<InterimFilter> findInterimFilters(final FilterChainBuilder builder)
            throws Exception {
        return findProperty(builder, FIELD_NAME_INTERIM_FILTERS, List.class);
    }

    private static FinalFilter findFinalFilter(final FilterChainBuilder builder)
            throws Exception {
        return findProperty(builder, FIELD_NAME_FINAL_FILTER, FinalFilter.class);
    }

    private static <P> P findProperty(final FilterChainBuilder builder,
                                      final String fieldName,
                                      final Class<P> propertyType)
            throws Exception {
        return findProperty(builder, fieldName, FilterChainBuilder.class, propertyType);
    }

    @SuppressWarnings("unchecked")
    private static List<InterimFilter> findInterimFilters(final FilterChain chain)
            throws Exception {
        return findProperty(chain, FIELD_NAME_INTERIM_FILTERS, List.class);
    }

    private static FinalFilter findFinalFilter(final FilterChain chain)
            throws Exception {
        return findProperty(chain, FIELD_NAME_FINAL_FILTER, FinalFilter.class);
    }

    private static <P> P findProperty(final FilterChain chain,
                                      final String fieldName,
                                      final Class<P> propertyType)
            throws Exception {
        return findProperty(chain, fieldName, FilterChain.class, propertyType);
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
