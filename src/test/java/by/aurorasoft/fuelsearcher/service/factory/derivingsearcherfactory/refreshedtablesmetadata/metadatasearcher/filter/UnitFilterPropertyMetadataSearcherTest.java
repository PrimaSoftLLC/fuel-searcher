//package by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher.filter;
//
//import by.aurorasoft.fuelsearcher.model.filter.interim.unit.UnitFilter;
//import by.aurorasoft.fuelsearcher.service.factory.derivingsearcherfactory.refreshedtablesmetadata.metadatasearcher.filter.UnitFilterPropertyMetadataSearcher;
//import by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil;
//import org.apache.poi.xwpf.usermodel.XWPFTableRow;
//import org.junit.Test;
//import org.mockito.MockedStatic;
//
//import java.util.List;
//import java.util.stream.Stream;
//
//import static by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.findRowsWithNotNullAndNotEmptyCell;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.ArgumentMatchers.same;
//import static org.mockito.Mockito.*;
//
//public final class UnitFilterPropertyMetadataSearcherTest {
//    private final UnitFilterPropertyMetadataSearcher searcher = new UnitFilterPropertyMetadataSearcher();
//
//    @Test
//    public void rowsWithPropertyValuesShouldBeFound() {
//        try (final MockedStatic<XWPFTableRowFilteringUtil> mockedFilteringUtil = mockStatic(XWPFTableRowFilteringUtil.class)) {
//            final int givenFiltrationCellIndex = 5;
//            final UnitFilter givenFilter = createFilter(givenFiltrationCellIndex);
//
//            final XWPFTableRow firstGivenRow = mock(XWPFTableRow.class);
//            final XWPFTableRow secondGivenRow = mock(XWPFTableRow.class);
//            final XWPFTableRow thirdGivenRow = mock(XWPFTableRow.class);
//            final XWPFTableRow fourthGivenRow = mock(XWPFTableRow.class);
//            final List<XWPFTableRow> givenSubTableDataRows = List.of(
//                    firstGivenRow,
//                    secondGivenRow,
//                    thirdGivenRow,
//                    fourthGivenRow
//            );
//
//            final Stream<XWPFTableRow> givenRowsWithPropertyValues = Stream.of(secondGivenRow, thirdGivenRow);
//            mockedFilteringUtil.when(
//                    () -> findRowsWithNotNullAndNotEmptyCell(
//                            same(givenSubTableDataRows),
//                            eq(givenFiltrationCellIndex)
//                    )
//            ).thenReturn(givenRowsWithPropertyValues);
//
//            final Stream<XWPFTableRow> actual = this.searcher.findRowsWithAllowableValues(
//                    givenSubTableDataRows,
//                    givenFilter
//            );
//            final List<XWPFTableRow> actualAsList = actual.toList();
//            final List<XWPFTableRow> expectedAsList = List.of(secondGivenRow, thirdGivenRow);
//            assertEquals(expectedAsList, actualAsList);
//        }
//    }
//
//    @SuppressWarnings("SameParameterValue")
//    private static UnitFilter createFilter(final int filtrationCellIndex) {
//        final UnitFilter filter = mock(UnitFilter.class);
//        when(filter.getFiltrationCellIndex()).thenReturn(filtrationCellIndex);
//        return filter;
//    }
//}
