package by.aurorasoft.fuelsearcher.util;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.isCellTextEqualIgnoringWhitespacesAndCase;
import static by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.*;
import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.isCellTextEqualIgnoringWhitespacesAndCase;
import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.isCellTextMatchRegex;
import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.isChildUnitedRow;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

public final class XWPFTableRowFilteringUtilTest {

    @Test
    public void unitedRowsShouldBeFound() {
        try (final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class)) {
            final String givenContent = "content";
            final int givenContentCellIndex = 3;

            final XWPFTableRow firstGivenRow = createRowMatchingContent(
                    true, mockedRowUtil, givenContentCellIndex, givenContent
            );

            final XWPFTableRow secondGivenRow = createRowMatchingContent(
                    true, mockedRowUtil, givenContentCellIndex, givenContent
            );
            final XWPFTableRow thirdGivenRow = createChildUnitedRow(mockedRowUtil, givenContentCellIndex);

            final XWPFTableRow fourthGivenRow = mock(XWPFTableRow.class);
            final XWPFTableRow fifthGivenRow = createChildUnitedRow(mockedRowUtil, givenContentCellIndex);

            final XWPFTableRow sixthGivenRow = createRowMatchingContent(
                    true, mockedRowUtil, givenContentCellIndex, givenContent
            );
            final XWPFTableRow seventhGivenRow = createChildUnitedRow(mockedRowUtil, givenContentCellIndex);
            final XWPFTableRow eighthGivenRow = createChildUnitedRow(mockedRowUtil, givenContentCellIndex);

            final List<XWPFTableRow> givenRows = List.of(
                    firstGivenRow,
                    secondGivenRow,
                    thirdGivenRow,
                    fourthGivenRow,
                    fifthGivenRow,
                    sixthGivenRow,
                    seventhGivenRow,
                    eighthGivenRow
            );

            final List<XWPFTableRow> actual = findUnitedRowsByContent(givenRows, givenContentCellIndex, givenContent);
            final List<XWPFTableRow> expected = List.of(
                    firstGivenRow,
                    secondGivenRow,
                    thirdGivenRow,
                    sixthGivenRow,
                    seventhGivenRow,
                    eighthGivenRow
            );
            assertEquals(expected, actual);
        }
    }

    @Test
    public void unitedRowsShouldNotBeFound() {
        try (final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class)) {
            final String givenContent = "content";
            final int givenContentCellIndex = 3;

            final XWPFTableRow firstGivenRow = createRowMatchingContent(
                    false, mockedRowUtil, givenContentCellIndex, givenContent
            );

            final XWPFTableRow secondGivenRow = createRowMatchingContent(
                    false, mockedRowUtil, givenContentCellIndex, givenContent
            );
            final XWPFTableRow thirdGivenRow = createChildUnitedRow(mockedRowUtil, givenContentCellIndex);

            final XWPFTableRow fourthGivenRow = mock(XWPFTableRow.class);
            final XWPFTableRow fifthGivenRow = createChildUnitedRow(mockedRowUtil, givenContentCellIndex);

            final XWPFTableRow sixthGivenRow = createRowMatchingContent(
                    false, mockedRowUtil, givenContentCellIndex, givenContent
            );
            final XWPFTableRow seventhGivenRow = createChildUnitedRow(mockedRowUtil, givenContentCellIndex);
            final XWPFTableRow eighthGivenRow = createChildUnitedRow(mockedRowUtil, givenContentCellIndex);

            final List<XWPFTableRow> givenRows = List.of(
                    firstGivenRow,
                    secondGivenRow,
                    thirdGivenRow,
                    fourthGivenRow,
                    fifthGivenRow,
                    sixthGivenRow,
                    seventhGivenRow,
                    eighthGivenRow
            );

            final List<XWPFTableRow> actual = findUnitedRowsByContent(givenRows, givenContentCellIndex, givenContent);
            assertTrue(actual.isEmpty());
        }
    }

    @Test
    public void firstRowShouldBeFoundByContent() {
        try (final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class)) {
            final String givenContent = "content";
            final int givenContentCellIndex = 3;

            final XWPFTableRow givenFirstRowMatchingContent = createRowMatchingContent(
                    true,
                    mockedRowUtil,
                    givenContentCellIndex,
                    givenContent
            );
            final List<XWPFTableRow> givenRows = List.of(
                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
                    givenFirstRowMatchingContent,
                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
                    createRowMatchingContent(true, mockedRowUtil, givenContentCellIndex, givenContent),
                    createRowMatchingContent(true, mockedRowUtil, givenContentCellIndex, givenContent),
                    createRowMatchingContent(true, mockedRowUtil, givenContentCellIndex, givenContent),
                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent)
            );

            final Optional<XWPFTableRow> optionalActual = findFirstRowByContent(
                    givenRows,
                    givenContentCellIndex,
                    givenContent
            );
            assertTrue(optionalActual.isPresent());
            final XWPFTableRow actual = optionalActual.get();
            assertSame(givenFirstRowMatchingContent, actual);
        }
    }

    @Test
    public void firstRowShouldNotBeFoundByContent() {
        try (final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class)) {
            final String givenContent = "content";
            final int givenContentCellIndex = 3;

            final List<XWPFTableRow> givenRows = List.of(
                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent),
                    createRowMatchingContent(false, mockedRowUtil, givenContentCellIndex, givenContent)
            );

            final Optional<XWPFTableRow> optionalActual = findFirstRowByContent(
                    givenRows,
                    givenContentCellIndex,
                    givenContent
            );
            assertTrue(optionalActual.isEmpty());
        }
    }

    @Test
    public void indexFirstCellShouldBeFoundByContent() {
        try (final MockedStatic<XWPFTableCellUtil> mockedCellUtil = mockStatic(XWPFTableCellUtil.class)) {
            final String givenContent = "content";
            final List<XWPFTableCell> givenCells = List.of(
                    createCellMatchingContent(false, mockedCellUtil, givenContent),
                    createCellMatchingContent(true, mockedCellUtil, givenContent),
                    createCellMatchingContent(true, mockedCellUtil, givenContent)
            );
            final XWPFTableRow givenRow = createRow(givenCells);

            final OptionalInt optionalActual = findIndexFirstCellByContent(givenRow, givenContent);
            assertTrue(optionalActual.isPresent());
            final int actual = optionalActual.getAsInt();
            final int expected = 1;
            assertEquals(expected, actual);
        }
    }

    @Test
    public void indexFirstCellShouldNotBeFoundByContent() {
        try (final MockedStatic<XWPFTableCellUtil> mockedCellUtil = mockStatic(XWPFTableCellUtil.class)) {
            final String givenContent = "content";
            final List<XWPFTableCell> givenCells = List.of(
                    createCellMatchingContent(false, mockedCellUtil, givenContent),
                    createCellMatchingContent(false, mockedCellUtil, givenContent),
                    createCellMatchingContent(false, mockedCellUtil, givenContent)
            );
            final XWPFTableRow givenRow = createRow(givenCells);

            final OptionalInt optionalActual = findIndexFirstCellByContent(givenRow, givenContent);
            assertTrue(optionalActual.isEmpty());
        }
    }

    @Test
    public void rowsShouldBeFoundByGroup() {
        throw new RuntimeException();
    }

    @SuppressWarnings("SameParameterValue")
    private static XWPFTableRow createRowMatchingContent(final boolean match,
                                                         final MockedStatic<XWPFTableRowUtil> mockedRowUtil,
                                                         final int contentCellIndex,
                                                         final String content) {
        final XWPFTableRow row = mock(XWPFTableRow.class);
        mockedRowUtil.when(
                () -> isCellTextEqualIgnoringWhitespacesAndCase(same(row), eq(contentCellIndex), same(content))
        ).thenReturn(match);
        return row;
    }

    @SuppressWarnings("SameParameterValue")
    private static XWPFTableRow createRowMatchingContentRegex(final boolean match,
                                                              final MockedStatic<XWPFTableRowUtil> mockedRowUtil,
                                                              final int contentCellIndex,
                                                              final String regex) {
        final XWPFTableRow row = mock(XWPFTableRow.class);
        mockedRowUtil.when(
                () -> isCellTextMatchRegex(same(row), eq(contentCellIndex), same(regex))
        ).thenReturn(match);
        return row;
    }

    @SuppressWarnings("SameParameterValue")
    private static XWPFTableRow createChildUnitedRow(final MockedStatic<XWPFTableRowUtil> mockedRowUtil,
                                                     final int contentCellIndex) {
        final XWPFTableRow row = mock(XWPFTableRow.class);
        mockedRowUtil.when(() -> isChildUnitedRow(same(row), eq(contentCellIndex))).thenReturn(true);
        return row;
    }

    @SuppressWarnings("SameParameterValue")
    private static XWPFTableCell createCellMatchingContent(final boolean match,
                                                           final MockedStatic<XWPFTableCellUtil> mockedCellUtil,
                                                           final String content) {
        final XWPFTableCell cell = mock(XWPFTableCell.class);
        mockedCellUtil.when(
                () -> isCellTextEqualIgnoringWhitespacesAndCase(same(cell), same(content))
        ).thenReturn(match);
        return cell;
    }

    private static XWPFTableRow createRow(final List<XWPFTableCell> cells) {
        final XWPFTableRow row = mock(XWPFTableRow.class);
        when(row.getTableCells()).thenReturn(cells);
        return row;
    }
}
