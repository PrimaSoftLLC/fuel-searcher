package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher.filter;

import by.aurorasoft.fuelsearcher.model.filter.interim.unit.UnitFilter;
import by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.isCellNullOrEmpty;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

public final class UnitFilterPropertyMetadataSearcherTest {
    private final UnitFilterPropertyMetadataSearcher searcher = new UnitFilterPropertyMetadataSearcher();

    @Test
    public void rowsWithPropertyValuesShouldBeFound() {
        try (final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class)) {
            final int givenFiltrationCellIndex = 5;
            final UnitFilter givenFilter = createFilter(givenFiltrationCellIndex);

            final XWPFTableRow firstGivenRow = mock(XWPFTableRow.class);
            mockedRowUtil.when(
                    () -> isCellNullOrEmpty(
                            same(firstGivenRow),
                            eq(givenFiltrationCellIndex)
                    )
            ).thenReturn(true);

            final XWPFTableRow secondGivenRow = mock(XWPFTableRow.class);
            mockedRowUtil.when(
                    () -> isCellNullOrEmpty(
                            same(secondGivenRow),
                            eq(givenFiltrationCellIndex)
                    )
            ).thenReturn(false);

            final XWPFTableRow thirdGivenRow = mock(XWPFTableRow.class);
            mockedRowUtil.when(
                    () -> isCellNullOrEmpty(
                            same(thirdGivenRow),
                            eq(givenFiltrationCellIndex)
                    )
            ).thenReturn(true);

            final XWPFTableRow fourthGivenRow = mock(XWPFTableRow.class);
            mockedRowUtil.when(
                    () -> isCellNullOrEmpty(
                            same(fourthGivenRow),
                            eq(givenFiltrationCellIndex)
                    )
            ).thenReturn(false);

            final List<XWPFTableRow> givenRows = List.of(firstGivenRow, secondGivenRow, thirdGivenRow, fourthGivenRow);

            final Stream<XWPFTableRow> actual = this.searcher.findRowsWithPropertyValues(givenRows, givenFilter);
            final List<XWPFTableRow> actualAsList = actual.toList();
            final List<XWPFTableRow> expectedAsList = List.of(secondGivenRow, fourthGivenRow);
            assertEquals(expectedAsList, actualAsList);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static UnitFilter createFilter(final int filtrationCellIndex) {
        final UnitFilter filter = mock(UnitFilter.class);
        when(filter.getFiltrationCellIndex()).thenReturn(filtrationCellIndex);
        return filter;
    }
}
