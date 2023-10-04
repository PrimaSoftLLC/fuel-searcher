package com.aurorasoft.fuelsearcher.model.filter;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class FilterTest {

    @Test
    public void rowsShouldBeFiltered() {
        final SpecificationPropertyExtractor givenFiltrationValueExtractor = mock(SpecificationPropertyExtractor.class);
        final int givenFiltrationCellIndex = 5;
        final List<XWPFTableRow> givenFilteringResultRows = List.of(
                mock(XWPFTableRow.class),
                mock(XWPFTableRow.class)
        );
        final TestFilter givenFilter = new TestFilter(
                givenFiltrationValueExtractor,
                givenFiltrationCellIndex,
                givenFilteringResultRows
        );
        final FuelSpecification givenSpecification = mock(FuelSpecification.class);

        final String givenFiltrationValue = "filtration-value";
        when(givenFiltrationValueExtractor.extract(same(givenSpecification))).thenReturn(givenFiltrationValue);

        final List<XWPFTableRow> givenFilteredRows = List.of(
                mock(XWPFTableRow.class),
                mock(XWPFTableRow.class)
        );

        final List<XWPFTableRow> actual = givenFilter.filter(givenFilteredRows, givenSpecification);
        assertSame(givenFilteringResultRows, actual);
    }

    private static final class TestFilter extends Filter<List<XWPFTableRow>> {
        private final List<XWPFTableRow> filteringResultRows;

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
            return this.filteringResultRows;
        }
    }
}
