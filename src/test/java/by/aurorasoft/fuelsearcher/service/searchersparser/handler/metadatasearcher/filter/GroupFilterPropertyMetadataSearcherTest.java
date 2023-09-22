package by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher.filter;

import by.aurorasoft.fuelsearcher.model.filter.interim.group.GroupFilter;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher.filter.GroupFilterPropertyMetadataSearcher;
import by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.findRowsWithCellMatchingRegex;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public final class GroupFilterPropertyMetadataSearcherTest {
    private final GroupFilterPropertyMetadataSearcher searcher = new GroupFilterPropertyMetadataSearcher();

    @Test
    public void rowsWithPropertyValuesShouldBeFound() {
        try (final MockedStatic<XWPFTableRowFilteringUtil> mockedFilteringUtil = mockStatic(XWPFTableRowFilteringUtil.class)) {
            final int givenFiltrationCellIndex = 5;
            final String givenGroupValueRegex = "group-value-regex";
            final GroupFilter givenFilter = createFilter(givenFiltrationCellIndex, givenGroupValueRegex);

            final XWPFTableRow firstGivenRow = mock(XWPFTableRow.class);
            final XWPFTableRow secondGivenRow = mock(XWPFTableRow.class);
            final XWPFTableRow thirdGivenRow = mock(XWPFTableRow.class);
            final XWPFTableRow fourthGivenRow = mock(XWPFTableRow.class);
            final List<XWPFTableRow> givenSubTableDataRows = List.of(
                    firstGivenRow,
                    secondGivenRow,
                    thirdGivenRow,
                    fourthGivenRow
            );

            final Stream<XWPFTableRow> givenRowsWithPropertyValues = Stream.of(secondGivenRow, thirdGivenRow);
            mockedFilteringUtil.when(
                    () -> findRowsWithCellMatchingRegex(
                            same(givenSubTableDataRows),
                            eq(givenFiltrationCellIndex),
                            same(givenGroupValueRegex)
                    )
            ).thenReturn(givenRowsWithPropertyValues);

            final Stream<XWPFTableRow> actual = this.searcher.findRowsWithAllowableValues(
                    givenSubTableDataRows,
                    givenFilter
            );
            final List<XWPFTableRow> actualAsList = actual.toList();
            final List<XWPFTableRow> expectedAsList = List.of(secondGivenRow, thirdGivenRow);
            assertEquals(expectedAsList, actualAsList);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static GroupFilter createFilter(final int filtrationCellIndex, final String groupValueRegex) {
        final GroupFilter filter = mock(GroupFilter.class);
        when(filter.getFiltrationCellIndex()).thenReturn(filtrationCellIndex);
        when(filter.findGroupValueRegex()).thenReturn(groupValueRegex);
        return filter;
    }
}
