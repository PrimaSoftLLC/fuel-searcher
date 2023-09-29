package by.aurorasoft.fuelsearcher.model.filter.conclusive;

import by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.Optional;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.findFirstRowByContent;
import static java.lang.Integer.MIN_VALUE;
import static java.util.Optional.empty;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public final class FinalFilterTest {

    @Test
    public void rowsShouldBeFilteredToRow() {
        try (final MockedStatic<XWPFTableRowFilteringUtil> mockedUtilities = mockStatic(XWPFTableRowFilteringUtil.class)) {
            final List<XWPFTableRow> givenRows = List.of(
                    mock(XWPFTableRow.class),
                    mock(XWPFTableRow.class)
            );
            final String givenFiltrationValue = "filtration-value";
            final int givenFiltrationCellIndex = 5;
            final TestFinalFilter givenFilter = new TestFinalFilter();

            final XWPFTableRow givenResultRow = mock(XWPFTableRow.class);
            mockedUtilities.when(
                    () -> findFirstRowByContent(
                            same(givenRows),
                            eq(givenFiltrationCellIndex),
                            same(givenFiltrationValue)
                    )
            ).thenReturn(Optional.of(givenResultRow));

            final Optional<XWPFTableRow> optionalActual = givenFilter.filter(
                    givenRows,
                    givenFiltrationValue,
                    givenFiltrationCellIndex
            );
            assertTrue(optionalActual.isPresent());
            final XWPFTableRow actual = optionalActual.get();
            assertSame(givenResultRow, actual);
        }
    }

    @Test
    public void allRowsShouldBeFiltered() {
        try (final MockedStatic<XWPFTableRowFilteringUtil> mockedUtilities = mockStatic(XWPFTableRowFilteringUtil.class)) {
            final List<XWPFTableRow> givenRows = List.of(
                    mock(XWPFTableRow.class),
                    mock(XWPFTableRow.class)
            );
            final String givenFiltrationValue = "filtration-value";
            final int givenFiltrationCellIndex = 5;
            final TestFinalFilter givenFilter = new TestFinalFilter();

            mockedUtilities.when(
                    () -> findFirstRowByContent(
                            same(givenRows),
                            eq(givenFiltrationCellIndex),
                            same(givenFiltrationValue)
                    )
            ).thenReturn(empty());

            final Optional<XWPFTableRow> optionalActual = givenFilter.filter(
                    givenRows,
                    givenFiltrationValue,
                    givenFiltrationCellIndex
            );
            assertTrue(optionalActual.isEmpty());
        }
    }

    private static final class TestFinalFilter extends FinalFilter {

        public TestFinalFilter() {
            super(null, MIN_VALUE);
        }

    }
}
