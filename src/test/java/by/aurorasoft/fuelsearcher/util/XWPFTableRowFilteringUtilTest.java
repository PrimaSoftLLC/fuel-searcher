package by.aurorasoft.fuelsearcher.util;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.OptionalInt;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.*;
import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.*;
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
            final int givenContentCellIndex = 3;
            final String givenContent = "content";

            final XWPFTableRow firstGivenRow = createRowMatchingContent(
                    false, mockedRowUtil, givenContentCellIndex, givenContent
            );
            final XWPFTableRow secondGivenRow = createRowMatchingContent(
                    true, mockedRowUtil, givenContentCellIndex, givenContent
            );
            final XWPFTableRow thirdGivenRow = createRowMatchingContent(
                    true, mockedRowUtil, givenContentCellIndex, givenContent
            );
            final List<XWPFTableRow> givenRows = List.of(firstGivenRow, secondGivenRow, thirdGivenRow);

            final OptionalInt actualOptional = findIndexFirstRowByContent(
                    givenRows, givenContentCellIndex, givenContent
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
            final int givenContentCellIndex = 3;
            final String givenContent = "content";

            final XWPFTableRow firstGivenRow = createRowMatchingContent(
                    false, mockedRowUtil, givenContentCellIndex, givenContent
            );
            final XWPFTableRow secondGivenRow = createRowMatchingContent(
                    false, mockedRowUtil, givenContentCellIndex, givenContent
            );
            final XWPFTableRow thirdGivenRow = createRowMatchingContent(
                    false, mockedRowUtil, givenContentCellIndex, givenContent
            );
            final List<XWPFTableRow> givenRows = List.of(firstGivenRow, secondGivenRow, thirdGivenRow);

            final OptionalInt actualOptional = findIndexFirstRowByContent(
                    givenRows, givenContentCellIndex, givenContent
            );
            assertTrue(actualOptional.isEmpty());
        }
    }

    @Test
    public void indexFirstRowByContentRegexShouldBeFound() {
        try (final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class)) {
            final int givenStartFindingIndex = 1;
            final int givenContentCellIndex = 3;
            final String givenRegex = "regex";

            final XWPFTableRow firstGivenRow = createRowMatchingContentRegex(
                    true, mockedRowUtil, givenContentCellIndex, givenRegex
            );
            final XWPFTableRow secondGivenRow = createRowMatchingContentRegex(
                    false, mockedRowUtil, givenContentCellIndex, givenRegex
            );
            final XWPFTableRow thirdGivenRow = createRowMatchingContentRegex(
                    false, mockedRowUtil, givenContentCellIndex, givenRegex
            );
            final XWPFTableRow fourthGivenRow = createRowMatchingContentRegex(
                    true, mockedRowUtil, givenContentCellIndex, givenRegex
            );
            final XWPFTableRow fifthGivenRow = createRowMatchingContentRegex(
                    true, mockedRowUtil, givenContentCellIndex, givenRegex
            );
            final List<XWPFTableRow> givenRows = List.of(
                    firstGivenRow, secondGivenRow, thirdGivenRow, fourthGivenRow, fifthGivenRow
            );

            final OptionalInt actualOptional = findIndexFirstRowByContentRegex(
                    givenRows, givenStartFindingIndex, givenContentCellIndex, givenRegex
            );
            assertTrue(actualOptional.isPresent());
            final int actual = actualOptional.getAsInt();
            final int expected = 3;
            assertEquals(expected, actual);
        }
    }

    @Test
    public void indexFirstRowByContentRegexShouldNotBeFound() {
        try (final MockedStatic<XWPFTableRowUtil> mockedRowUtil = mockStatic(XWPFTableRowUtil.class)) {
            final int givenStartFindingIndex = 3;
            final int givenContentCellIndex = 3;
            final String givenRegex = "regex";

            final XWPFTableRow firstGivenRow = createRowMatchingContentRegex(
                    true, mockedRowUtil, givenContentCellIndex, givenRegex
            );
            final XWPFTableRow secondGivenRow = createRowMatchingContentRegex(
                    false, mockedRowUtil, givenContentCellIndex, givenRegex
            );
            final XWPFTableRow thirdGivenRow = createRowMatchingContentRegex(
                    true, mockedRowUtil, givenContentCellIndex, givenRegex
            );
            final XWPFTableRow fourthGivenRow = createRowMatchingContentRegex(
                    false, mockedRowUtil, givenContentCellIndex, givenRegex
            );
            final XWPFTableRow fifthGivenRow = createRowMatchingContentRegex(
                    false, mockedRowUtil, givenContentCellIndex, givenRegex
            );
            final List<XWPFTableRow> givenRows = List.of(
                    firstGivenRow, secondGivenRow, thirdGivenRow, fourthGivenRow, fifthGivenRow
            );

            final OptionalInt actualOptional = findIndexFirstRowByContentRegex(
                    givenRows, givenStartFindingIndex, givenContentCellIndex, givenRegex
            );
            assertTrue(actualOptional.isEmpty());
        }
    }

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

    private static XWPFTableRow createChildUnitedRow(final MockedStatic<XWPFTableRowUtil> mockedRowUtil,
                                                     final int contentCellIndex) {
        final XWPFTableRow row = mock(XWPFTableRow.class);
        mockedRowUtil.when(() -> isChildUnitedRow(same(row), eq(contentCellIndex))).thenReturn(true);
        return row;
    }
}
