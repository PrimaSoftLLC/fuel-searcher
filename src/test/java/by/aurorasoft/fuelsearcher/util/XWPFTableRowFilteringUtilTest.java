package by.aurorasoft.fuelsearcher.util;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.OptionalInt;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.findIndexFirstRowByContent;
import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.isCellTextEqualIgnoringWhitespacesAndCase;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public final class XWPFTableRowFilteringUtilTest {

    @Test
    public void indexFirstRowByContentShouldBeFound() {
        try (final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class)) {
            final int givenCellIndexWithContent = 3;
            final String givenContent = "content";

            final XWPFTableRow firstGivenRow = createRow(
                    false, mockedRowUtil, givenCellIndexWithContent, givenContent
            );
            final XWPFTableRow secondGivenRow = createRow(
                    true, mockedRowUtil, givenCellIndexWithContent, givenContent
            );
            final XWPFTableRow thirdGivenRow = createRow(
                    true, mockedRowUtil, givenCellIndexWithContent, givenContent
            );
            final List<XWPFTableRow> givenRows = List.of(firstGivenRow, secondGivenRow, thirdGivenRow);

            final OptionalInt actualOptional = findIndexFirstRowByContent(
                    givenRows, givenCellIndexWithContent, givenContent
            );
            assertTrue(actualOptional.isPresent());
            final int actual = actualOptional.getAsInt();
            final int expected = 1;
            assertEquals(expected, actual);
        }
    }

    @Test
    public void indexFirstRowByContentShouldNotBeFound() {
        try (final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class)) {
            final int givenCellIndexWithContent = 3;
            final String givenContent = "content";

            final XWPFTableRow firstGivenRow = createRow(
                    false, mockedRowUtil, givenCellIndexWithContent, givenContent
            );
            final XWPFTableRow secondGivenRow = createRow(
                    false, mockedRowUtil, givenCellIndexWithContent, givenContent
            );
            final XWPFTableRow thirdGivenRow = createRow(
                    false, mockedRowUtil, givenCellIndexWithContent, givenContent
            );
            final List<XWPFTableRow> givenRows = List.of(firstGivenRow, secondGivenRow, thirdGivenRow);

            final OptionalInt actualOptional = findIndexFirstRowByContent(
                    givenRows, givenCellIndexWithContent, givenContent
            );
            assertTrue(actualOptional.isEmpty());
        }
    }

    private static XWPFTableRow createRow(final boolean equal,
                                          final MockedStatic<XWPFTableRowUtil> mockedRowUtil,
                                          final int cellIndexWithContent,
                                          final String content) {
        final XWPFTableRow row = mock(XWPFTableRow.class);
        mockedRowUtil.when(
                () -> isCellTextEqualIgnoringWhitespacesAndCase(same(row), eq(cellIndexWithContent), same(content))
        ).thenReturn(equal);
        return row;
    }
}
