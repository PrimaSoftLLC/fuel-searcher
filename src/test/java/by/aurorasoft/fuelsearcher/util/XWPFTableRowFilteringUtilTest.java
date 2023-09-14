package by.aurorasoft.fuelsearcher.util;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.findUnitedRowsByContent;
import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class XWPFTableRowFilteringUtilTest extends AbstractContextTest {
    private static final String FIRST_TABLE_NAME = "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ";

    private static final int INDEX_FIRST_ROW_FILTERED_BY_GROUP = 5;
    private static final int INDEX_SECOND_ROW_FILTERED_BY_GROUP = 52;

    @Autowired
    private FuelDocument fuelDocument;

    private List<XWPFTableRow> rowsFilteredByGroup;

    @Before
    public void initializeRows() {
        final List<XWPFTableRow> tableRows = findSubTableRowsOfFirstTable(this.fuelDocument);
        this.rowsFilteredByGroup = extractRowsFilteredByGroup(tableRows);
    }

    @Test
    public void unitedRowsShouldBeFoundByContent() {
        final int givenContentCellIndex = 2;
        final String givenContent = "ППУ-13";

        final List<XWPFTableRow> actual = findUnitedRowsByContent(
                this.rowsFilteredByGroup,
                givenContentCellIndex,
                givenContent
        );

        final List<Integer> expectedRowIndexes = List.of(8, 9, 10, 11, 16, 17, 18, 19);
        assertEquals(expectedRowIndexes.size(), actual.size());

        final boolean rowFilteringSuccess = isRowFilteringSuccess(this.rowsFilteredByGroup, actual, expectedRowIndexes);
        assertTrue(rowFilteringSuccess);
    }

    @Test
    public void unitedRowsShouldNotBeFoundByContent() {
        final int givenContentCellIndex = 2;
        final String givenContent = "not-existing-mechanism";

        final List<XWPFTableRow> actual = findUnitedRowsByContent(
                this.rowsFilteredByGroup,
                givenContentCellIndex,
                givenContent
        );
        assertTrue(actual.isEmpty());
    }

    @Test
    public void firstRowShouldBeFoundByContent() {
        throw new RuntimeException();
    }

    private static List<XWPFTableRow> findSubTableRowsOfFirstTable(final FuelDocument fuelDocument) {
        return fuelDocument.getTables()
                .stream()
                .filter(XWPFTableRowFilteringUtilTest::isFirstTable)
                .findFirst()
                .map(FuelTable::getElements)
                .map(elements -> elements.get(0))
                .map(element -> (XWPFTable) element)
                .map(XWPFTable::getRows)
                .orElseThrow(() -> new IllegalArgumentException("Table for testing wasn't found"));
    }

    private static boolean isFirstTable(final FuelTable table) {
        final String tableName = table.getName();
        return Objects.equals(tableName, FIRST_TABLE_NAME);
    }

    private static List<XWPFTableRow> extractRowsFilteredByGroup(final List<XWPFTableRow> tableRows) {
        return tableRows.subList(INDEX_FIRST_ROW_FILTERED_BY_GROUP, INDEX_SECOND_ROW_FILTERED_BY_GROUP + 1);
    }

    private static boolean isRowFilteringSuccess(final List<XWPFTableRow> initialRows,
                                                 final List<XWPFTableRow> actualFilteredRows,
                                                 final List<Integer> expectedFilteredRowIndexes) {
        return range(0, actualFilteredRows.size()).allMatch(
                i -> isRowFilteringSuccess(i, initialRows, actualFilteredRows, expectedFilteredRowIndexes)
        );
    }

    private static boolean isRowFilteringSuccess(final int researchActualFilteredRowIndex,
                                                 final List<XWPFTableRow> initialRows,
                                                 final List<XWPFTableRow> actualFilteredRows,
                                                 final List<Integer> expectedFilteredRowIndexes) {
        final XWPFTableRow actualRow = actualFilteredRows.get(researchActualFilteredRowIndex);
        final int expectedRowIndex = expectedFilteredRowIndexes.get(researchActualFilteredRowIndex);
        final XWPFTableRow expectedRow = initialRows.get(expectedRowIndex);
        return expectedRow == actualRow;
    }

//    @Test
//    public void unitedRowsShouldBeFound() {
//        try (final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class)) {
//            final String givenContent = "content";
//            final int givenContentCellIndex = 3;
//
//            final XWPFTableRow firstGivenRow = createRowMatchingContent(
//                    true, mockedRowUtil, givenContentCellIndex, givenContent
//            );
//
//            final XWPFTableRow secondGivenRow = createRowMatchingContent(
//                    true, mockedRowUtil, givenContentCellIndex, givenContent
//            );
//            final XWPFTableRow thirdGivenRow = createChildUnitedRow(mockedRowUtil, givenContentCellIndex);
//
//            final XWPFTableRow fourthGivenRow = mock(XWPFTableRow.class);
//            final XWPFTableRow fifthGivenRow = createChildUnitedRow(mockedRowUtil, givenContentCellIndex);
//
//            final XWPFTableRow sixthGivenRow = createRowMatchingContent(
//                    true, mockedRowUtil, givenContentCellIndex, givenContent
//            );
//            final XWPFTableRow seventhGivenRow = createChildUnitedRow(mockedRowUtil, givenContentCellIndex);
//            final XWPFTableRow eighthGivenRow = createChildUnitedRow(mockedRowUtil, givenContentCellIndex);
//
//            final List<XWPFTableRow> givenRows = List.of(
//                    firstGivenRow,
//                    secondGivenRow,
//                    thirdGivenRow,
//                    fourthGivenRow,
//                    fifthGivenRow,
//                    sixthGivenRow,
//                    seventhGivenRow,
//                    eighthGivenRow
//            );
//
//            final List<XWPFTableRow> actual = findUnitedRowsByContent(givenRows, givenContentCellIndex, givenContent);
//            final List<XWPFTableRow> expected = List.of(
//                    firstGivenRow,
//                    secondGivenRow,
//                    thirdGivenRow,
//                    sixthGivenRow,
//                    seventhGivenRow,
//                    eighthGivenRow
//            );
//            assertEquals(expected, actual);
//        }
//    }
//
//    @Test
//    public void unitedRowsShouldNotBeFound() {
//        try (final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class)) {
//            final String givenContent = "content";
//            final int givenContentCellIndex = 3;
//
//            final XWPFTableRow firstGivenRow = createRowMatchingContent(
//                    false, mockedRowUtil, givenContentCellIndex, givenContent
//            );
//
//            final XWPFTableRow secondGivenRow = createRowMatchingContent(
//                    false, mockedRowUtil, givenContentCellIndex, givenContent
//            );
//            final XWPFTableRow thirdGivenRow = createChildUnitedRow(mockedRowUtil, givenContentCellIndex);
//
//            final XWPFTableRow fourthGivenRow = mock(XWPFTableRow.class);
//            final XWPFTableRow fifthGivenRow = createChildUnitedRow(mockedRowUtil, givenContentCellIndex);
//
//            final XWPFTableRow sixthGivenRow = createRowMatchingContent(
//                    false, mockedRowUtil, givenContentCellIndex, givenContent
//            );
//            final XWPFTableRow seventhGivenRow = createChildUnitedRow(mockedRowUtil, givenContentCellIndex);
//            final XWPFTableRow eighthGivenRow = createChildUnitedRow(mockedRowUtil, givenContentCellIndex);
//
//            final List<XWPFTableRow> givenRows = List.of(
//                    firstGivenRow,
//                    secondGivenRow,
//                    thirdGivenRow,
//                    fourthGivenRow,
//                    fifthGivenRow,
//                    sixthGivenRow,
//                    seventhGivenRow,
//                    eighthGivenRow
//            );
//
//            final List<XWPFTableRow> actual = findUnitedRowsByContent(givenRows, givenContentCellIndex, givenContent);
//            assertTrue(actual.isEmpty());
//        }
//    }
//
//    @Test
//    public void firstRowShouldBeFoundByContent() {
//        try (final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class)) {
//            final String givenContent = "content";
//            final int givenContentCellIndex = 3;
//
//            final XWPFTableRow givenFirstRowMatchingContent = createRowMatchingContent(
//                    true,
//                    mockedRowUtil,
//                    givenContentCellIndex,
//                    givenContent
//            );
//            final List<XWPFTableRow> givenRows = List.of(
//                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
//                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
//                    givenFirstRowMatchingContent,
//                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
//                    createRowMatchingContent(true, mockedRowUtil, givenContentCellIndex, givenContent),
//                    createRowMatchingContent(true, mockedRowUtil, givenContentCellIndex, givenContent),
//                    createRowMatchingContent(true, mockedRowUtil, givenContentCellIndex, givenContent),
//                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent)
//            );
//
//            final Optional<XWPFTableRow> optionalActual = findFirstRowByContent(
//                    givenRows,
//                    givenContentCellIndex,
//                    givenContent
//            );
//            assertTrue(optionalActual.isPresent());
//            final XWPFTableRow actual = optionalActual.get();
//            assertSame(givenFirstRowMatchingContent, actual);
//        }
//    }
//
//    @Test
//    public void firstRowShouldNotBeFoundByContent() {
//        try (final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class)) {
//            final String givenContent = "content";
//            final int givenContentCellIndex = 3;
//
//            final List<XWPFTableRow> givenRows = List.of(
//                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
//                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
//                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
//                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
//                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
//                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
//                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
//                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent)
//            );
//
//            final Optional<XWPFTableRow> optionalActual = findFirstRowByContent(
//                    givenRows,
//                    givenContentCellIndex,
//                    givenContent
//            );
//            assertTrue(optionalActual.isEmpty());
//        }
//    }
//
//    @Test
//    public void indexFirstCellShouldBeFoundByContent() {
//        try (final MockedStatic<XWPFTableCellUtil> mockedCellUtil = mockStatic(XWPFTableCellUtil.class)) {
//            final String givenContent = "content";
//            final List<XWPFTableCell> givenCells = List.of(
//                    createCellMatchingContent(false, mockedCellUtil, givenContent),
//                    createCellMatchingContent(true, mockedCellUtil, givenContent),
//                    createCellMatchingContent(true, mockedCellUtil, givenContent)
//            );
//            final XWPFTableRow givenRow = createRow(givenCells);
//
//            final OptionalInt optionalActual = findIndexFirstCellByContent(givenRow, givenContent);
//            assertTrue(optionalActual.isPresent());
//            final int actual = optionalActual.getAsInt();
//            final int expected = 1;
//            assertEquals(expected, actual);
//        }
//    }
//
//    @Test
//    public void indexFirstCellShouldNotBeFoundByContent() {
//        try (final MockedStatic<XWPFTableCellUtil> mockedCellUtil = mockStatic(XWPFTableCellUtil.class)) {
//            final String givenContent = "content";
//            final List<XWPFTableCell> givenCells = List.of(
//                    createCellMatchingContent(false, mockedCellUtil, givenContent),
//                    createCellMatchingContent(false, mockedCellUtil, givenContent),
//                    createCellMatchingContent(false, mockedCellUtil, givenContent)
//            );
//            final XWPFTableRow givenRow = createRow(givenCells);
//
//            final OptionalInt optionalActual = findIndexFirstCellByContent(givenRow, givenContent);
//            assertTrue(optionalActual.isEmpty());
//        }
//    }
//
//    @Test
//    public void rowsShouldBeFoundByGroup() {
//        try (final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class)) {
//
//        }
//    }
//
//    @SuppressWarnings("SameParameterValue")
//    private static XWPFTableRow createRowMatchingContent(final boolean match,
//                                                         final MockedStatic<XWPFTableRowUtil> mockedRowUtil,
//                                                         final int contentCellIndex,
//                                                         final String content) {
//        final XWPFTableRow row = mock(XWPFTableRow.class);
//        mockedRowUtil.when(
//                () -> isCellTextEqualIgnoringWhitespacesAndCase(same(row), eq(contentCellIndex), same(content))
//        ).thenReturn(match);
//        return row;
//    }
//
//    @SuppressWarnings("SameParameterValue")
//    private static XWPFTableRow createRowMatchingContentRegex(final boolean match,
//                                                              final MockedStatic<XWPFTableRowUtil> mockedRowUtil,
//                                                              final int contentCellIndex,
//                                                              final String regex) {
//        final XWPFTableRow row = mock(XWPFTableRow.class);
//        mockedRowUtil.when(
//                () -> isCellTextMatchRegex(same(row), eq(contentCellIndex), same(regex))
//        ).thenReturn(match);
//        return row;
//    }
//
//    @SuppressWarnings("SameParameterValue")
//    private static XWPFTableRow createChildUnitedRow(final MockedStatic<XWPFTableRowUtil> mockedRowUtil,
//                                                     final int contentCellIndex) {
//        final XWPFTableRow row = mock(XWPFTableRow.class);
//        mockedRowUtil.when(() -> isChildUnitedRow(same(row), eq(contentCellIndex))).thenReturn(true);
//        return row;
//    }
//
//    @SuppressWarnings("SameParameterValue")
//    private static XWPFTableCell createCellMatchingContent(final boolean match,
//                                                           final MockedStatic<XWPFTableCellUtil> mockedCellUtil,
//                                                           final String content) {
//        final XWPFTableCell cell = mock(XWPFTableCell.class);
//        mockedCellUtil.when(
//                () -> isCellTextEqualIgnoringWhitespacesAndCase(same(cell), same(content))
//        ).thenReturn(match);
//        return cell;
//    }
//
//    private static XWPFTableRow createRow(final List<XWPFTableCell> cells) {
//        final XWPFTableRow row = mock(XWPFTableRow.class);
//        when(row.getTableCells()).thenReturn(cells);
//        return row;
//    }
}
