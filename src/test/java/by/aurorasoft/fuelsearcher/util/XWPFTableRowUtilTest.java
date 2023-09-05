package by.aurorasoft.fuelsearcher.util;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;
import org.mockito.MockedStatic;

import static by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.extractDouble;
import static by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.extractText;
import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public final class XWPFTableRowUtilTest {

    @Test
    public void cellDoubleValueShouldBeExtracted() {
        final XWPFTableRow givenRow = mock(XWPFTableRow.class);
        final int givenCellIndex = 5;
        final XWPFTableCell givenCell = mock(XWPFTableCell.class);
        when(givenRow.getCell(eq(givenCellIndex))).thenReturn(givenCell);

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
        final XWPFTableCell givenCell = mock(XWPFTableCell.class);
        when(givenRow.getCell(eq(givenCellIndex))).thenReturn(givenCell);

        try (final MockedStatic<XWPFTableCellUtil> mockedCellUtil = mockStatic(XWPFTableCellUtil.class)) {
            final String givenText = "cell-text";
            mockedCellUtil.when(() -> extractText(same(givenCell))).thenReturn(givenText);

            final String actual = extractCellText(givenRow, givenCellIndex);
            assertSame(givenText, actual);
        }
    }

    @Test
    public void cellTextShouldBeEqualGivenString() {
        final XWPFTableRow givenRow = mock(XWPFTableRow.class);
        final int givenCellIndex = 5;
        final XWPFTableCell givenCell = mock(XWPFTableCell.class);
        when(givenRow.getCell(eq(givenCellIndex))).thenReturn(givenCell);

        try (final MockedStatic<XWPFTableCellUtil> mockedCellUtil = mockStatic(XWPFTableCellUtil.class)) {
            final String givenText = "cell-text";
            mockedCellUtil.when(() -> extractText(same(givenCell))).thenReturn(givenText);

            final String givenExpected = "cell-text";

            final boolean actual = isCellTextEqual(givenRow, givenCellIndex, givenExpected);
            assertTrue(actual);
        }
    }

    @Test
    public void cellTextShouldNotBeEqualGivenString() {
        throw new RuntimeException();
    }

}
