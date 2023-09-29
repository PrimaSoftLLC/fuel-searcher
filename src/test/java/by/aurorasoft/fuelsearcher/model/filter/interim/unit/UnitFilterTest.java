package by.aurorasoft.fuelsearcher.model.filter.interim.unit;

import by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.findUnitedRowsByContent;
import static java.lang.Integer.MIN_VALUE;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public final class UnitFilterTest {

    @Test
    public void rowsShouldBeFiltered() {
        try (final MockedStatic<XWPFTableRowFilteringUtil> mockedUtilities = mockStatic(XWPFTableRowFilteringUtil.class)) {
            final List<XWPFTableRow> givenRows = List.of(
                    mock(XWPFTableRow.class),
                    mock(XWPFTableRow.class),
                    mock(XWPFTableRow.class)
            );
            final String givenFiltrationValue = "filtration-value";
            final int givenFiltrationCellIndex = 5;
            final TestUnitFilter givenFilter = new TestUnitFilter();

            final List<XWPFTableRow> givenResultRows = List.of(mock(XWPFTableRow.class), mock(XWPFTableRow.class));
            mockedUtilities.when(
                    () -> findUnitedRowsByContent(
                            same(givenRows),
                            eq(givenFiltrationCellIndex),
                            same(givenFiltrationValue)
                    )
            ).thenReturn(givenResultRows);

            final List<XWPFTableRow> actual = givenFilter.filter(
                    givenRows,
                    givenFiltrationValue,
                    givenFiltrationCellIndex
            );
            assertSame(givenResultRows, actual);
        }
    }

    private static final class TestUnitFilter extends UnitFilter {

        public TestUnitFilter() {
            super(null, MIN_VALUE);
        }

    }
}
