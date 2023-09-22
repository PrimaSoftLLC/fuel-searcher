package by.aurorasoft.fuelsearcher.model.filter;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import lombok.Getter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;

import java.util.List;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class FilterTest {

    @Test
    public void propertyNameShouldBeFound() {
        final String givenPropertyName = "property-name";
        final SpecificationPropertyExtractor givenFiltrationValueExtractor = createFiltrationValueExtractor(
                givenPropertyName
        );
        final TestFilter givenFilter = new TestFilter(givenFiltrationValueExtractor);

        final String actual = givenFilter.findPropertyName();
        assertSame(givenPropertyName, actual);
    }

    @Test
    public void rowsShouldBeFiltered() {
        final SpecificationPropertyExtractor givenFiltrationValueExtractor = mock(SpecificationPropertyExtractor.class);
        final int givenFiltrationCellIndex = 5;
        final List<XWPFTableRow> givenFilteringResultRows = List.of(mock(XWPFTableRow.class), mock(XWPFTableRow.class));
        final TestFilter givenFilter = new TestFilter(
                givenFiltrationValueExtractor, givenFiltrationCellIndex, givenFilteringResultRows
        );
        final FuelSpecification givenSpecification = mock(FuelSpecification.class);

        final String givenFiltrationValue = "filtration-value";
        when(givenFiltrationValueExtractor.extract(same(givenSpecification))).thenReturn(givenFiltrationValue);

        final List<XWPFTableRow> givenFilteredRows = List.of(mock(XWPFTableRow.class), mock(XWPFTableRow.class));

        final List<XWPFTableRow> actual = givenFilter.filter(givenFilteredRows, givenSpecification);
        assertSame(givenFilteringResultRows, actual);

        final List<XWPFTableRow> actualFilteredRows = givenFilter.getLastFilteredRows();
        assertSame(givenFilteredRows, actualFilteredRows);

        final String actualFiltrationValue = givenFilter.getLastFiltrationValue();
        assertSame(givenFiltrationValue, actualFiltrationValue);

        final int actualFiltrationCellIndex = givenFilter.getFiltrationCellIndex();
        assertEquals(givenFiltrationCellIndex, actualFiltrationCellIndex);
    }

    @SuppressWarnings("SameParameterValue")
    private static SpecificationPropertyExtractor createFiltrationValueExtractor(final String propertyName) {
        final SpecificationPropertyExtractor extractor = mock(SpecificationPropertyExtractor.class);
        when(extractor.getPropertyName()).thenReturn(propertyName);
        return extractor;
    }

    private static final class TestFilter extends Filter<List<XWPFTableRow>> {
        private final List<XWPFTableRow> filteringResultRows;

        @Getter
        private List<XWPFTableRow> lastFilteredRows;

        @Getter
        private String lastFiltrationValue;

        @Getter
        private int lastFiltrationCellIndex;

        public TestFilter(final SpecificationPropertyExtractor filtrationValueExtractor) {
            this(filtrationValueExtractor, MIN_VALUE, null);
        }

        public TestFilter(final SpecificationPropertyExtractor filtrationValueExtractor,
                          final int filtrationCellIndex,
                          final List<XWPFTableRow> filteringResultRows) {
            super(filtrationValueExtractor, filtrationCellIndex);
            this.filteringResultRows = filteringResultRows;
        }

        @Override
        protected List<XWPFTableRow> filter(final List<XWPFTableRow> rows,
                                            final String filtrationValue,
                                            final int filtrationCellIndex) {
            this.lastFilteredRows = rows;
            this.lastFiltrationValue = filtrationValue;
            this.lastFiltrationCellIndex = filtrationCellIndex;
            return this.filteringResultRows;
        }
    }
}
