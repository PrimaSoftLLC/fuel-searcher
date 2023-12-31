package com.aurorasoft.fuelsearcher.util;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;
import org.mockito.MockedStatic;

import static com.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.isCellTextEqualConsideringOnlyLettersAndDigits;
import static com.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.isCellTextMatchRegex;
import static com.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.*;
import static com.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.isCellTextEqualIgnoringWhitespacesAndCase;
import static com.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.isCellTextMatchRegex;
import static com.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public final class XWPFTableRowUtilTest {

    @Test
    public void cellDoubleValueShouldBeExtracted() {
        final XWPFTableRow givenRow = mock(XWPFTableRow.class);
        final int givenCellIndex = 5;
        final XWPFTableCell givenCell = createRowCell(givenRow, givenCellIndex);

        try (final MockedStatic<XWPFTableCellUtil> mockedCellUtil = mockStatic(XWPFTableCellUtil.class)) {
            final double givenValue = 5.5;
            mockedCellUtil.when(() -> extractDouble(same(givenCell))).thenReturn(givenValue);

            final double actual = extractCellDoubleValue(givenRow, givenCellIndex);
            assertEquals(givenValue, actual, 0.);
        }
    }

    @Test
    public void cellTextShouldBeExtracted() {
        final XWPFTableRow givenRow = mock(XWPFTableRow.class);
        final int givenCellIndex = 5;
        final XWPFTableCell givenCell = createRowCell(givenRow, givenCellIndex);

        try (final MockedStatic<XWPFTableCellUtil> mockedCellUtil = mockStatic(XWPFTableCellUtil.class)) {
            final String givenText = "cell-text";
            mockedCellUtil.when(() -> extractText(same(givenCell))).thenReturn(givenText);

            final String actual = extractCellText(givenRow, givenCellIndex);
            assertSame(givenText, actual);
        }
    }

    @Test
    public void cellTextShouldBeEqualGivenStringIgnoringWhitespacesAndCase() {
        final XWPFTableRow givenRow = mock(XWPFTableRow.class);
        final int givenCellIndex = 6;
        final XWPFTableCell givenCell = createRowCell(givenRow, givenCellIndex);

        try (final MockedStatic<XWPFTableCellUtil> mockedCellUtil = mockStatic(XWPFTableCellUtil.class)) {
            final String givenExpected = "cell-text";

            mockedCellUtil.when(
                    () -> isCellTextEqualConsideringOnlyLettersAndDigits(same(givenCell), same(givenExpected))
            ).thenReturn(true);

            final boolean actual = isCellTextEqualIgnoringWhitespacesAndCase(givenRow, givenCellIndex, givenExpected);
            assertTrue(actual);
        }
    }

    @Test
    public void cellTextShouldNotBeEqualGivenStringIgnoringWhitespacesAndCase() {
        final XWPFTableRow givenRow = mock(XWPFTableRow.class);
        final int givenCellIndex = 6;
        final XWPFTableCell givenCell = createRowCell(givenRow, givenCellIndex);

        try (final MockedStatic<XWPFTableCellUtil> mockedCellUtil = mockStatic(XWPFTableCellUtil.class)) {
            final String givenExpected = "cell-text";

            mockedCellUtil.when(
                    () -> isCellTextEqualConsideringOnlyLettersAndDigits(same(givenCell), same(givenExpected))
            ).thenReturn(false);

            final boolean actual = isCellTextEqualIgnoringWhitespacesAndCase(givenRow, givenCellIndex, givenExpected);
            assertFalse(actual);
        }
    }

    @Test
    public void cellTextShouldMatchGivenRegex() {
        final XWPFTableRow givenRow = mock(XWPFTableRow.class);
        final int givenCellIndex = 6;
        final XWPFTableCell givenCell = createRowCell(givenRow, givenCellIndex);

        try (final MockedStatic<XWPFTableCellUtil> mockedCellUtil = mockStatic(XWPFTableCellUtil.class)) {
            final String givenRegex = "cell-text";

            mockedCellUtil.when(
                    () -> isCellTextMatchRegex(same(givenCell), same(givenRegex))
            ).thenReturn(true);

            final boolean actual = isCellTextMatchRegex(givenRow, givenCellIndex, givenRegex);
            assertTrue(actual);
        }
    }

    @Test
    public void cellTextShouldNotMatchGivenRegex() {
        final XWPFTableRow givenRow = mock(XWPFTableRow.class);
        final int givenCellIndex = 6;
        final XWPFTableCell givenCell = createRowCell(givenRow, givenCellIndex);

        try (final MockedStatic<XWPFTableCellUtil> mockedCellUtil = mockStatic(XWPFTableCellUtil.class)) {
            final String givenRegex = "cell-text";

            mockedCellUtil.when(
                    () -> isCellTextMatchRegex(same(givenCell), same(givenRegex))
            ).thenReturn(false);

            final boolean actual = isCellTextMatchRegex(givenRow, givenCellIndex, givenRegex);
            assertFalse(actual);
        }
    }

    @Test
    public void rowCellShouldBeNullOrEmptyBecauseOfCellContentIsNull() {
        final XWPFTableRow givenRow = mock(XWPFTableRow.class);
        final int givenContentCellIndex = 3;

        final boolean actual = isCellNullOrEmpty(givenRow, givenContentCellIndex);
        assertTrue(actual);
    }

    @Test
    public void rowCellShouldBeNullOrEmptyBecauseOfCellContentIsEmpty() {
        try (final MockedStatic<XWPFTableCellUtil> mockedCellUtil = mockStatic(XWPFTableCellUtil.class)) {
            final XWPFTableCell givenCell = mock(XWPFTableCell.class);
            mockedCellUtil.when(() -> isEmpty(same(givenCell))).thenReturn(true);

            final XWPFTableRow givenRow = mock(XWPFTableRow.class);
            final int givenContentCellIndex = 3;
            when(givenRow.getCell(eq(givenContentCellIndex))).thenReturn(givenCell);

            final boolean actual = isCellNullOrEmpty(givenRow, givenContentCellIndex);
            assertTrue(actual);
        }
    }

    @Test
    public void rowCellShouldNotBeNullOrEmpty() {
        try (final MockedStatic<XWPFTableCellUtil> mockedCellUtil = mockStatic(XWPFTableCellUtil.class)) {
            final XWPFTableCell givenCell = mock(XWPFTableCell.class);
            mockedCellUtil.when(() -> isEmpty(same(givenCell))).thenReturn(false);

            final XWPFTableRow givenRow = mock(XWPFTableRow.class);
            final int givenContentCellIndex = 3;
            when(givenRow.getCell(eq(givenContentCellIndex))).thenReturn(givenCell);

            final boolean actual = isCellNullOrEmpty(givenRow, givenContentCellIndex);
            assertFalse(actual);
        }
    }

    private static XWPFTableCell createRowCell(final XWPFTableRow row, final int cellIndex) {
        final XWPFTableCell cell = mock(XWPFTableCell.class);
        when(row.getCell(eq(cellIndex))).thenReturn(cell);
        return cell;
    }

}
