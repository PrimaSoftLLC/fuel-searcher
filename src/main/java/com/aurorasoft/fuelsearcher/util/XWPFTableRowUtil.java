package com.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.function.BiPredicate;
import java.util.function.Function;

import static com.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.isEmpty;

@UtilityClass
public final class XWPFTableRowUtil {

    public static double extractCellDoubleValue(final XWPFTableRow row, final int cellIndex) {
        return extractCellValue(row, cellIndex, XWPFTableCellUtil::extractDouble);
    }

    public static String extractCellText(final XWPFTableRow row, final int cellIndex) {
        return extractCellValue(row, cellIndex, XWPFTableCellUtil::extractText);
    }

    public static boolean isCellTextEqualIgnoringWhitespacesAndCase(final XWPFTableRow row,
                                                                    final int cellIndex,
                                                                    final String expected) {
        return isCellTextMatch(row, cellIndex, expected, XWPFTableCellUtil::isCellTextEqualIgnoringWhitespacesAndCase);
    }

    public static boolean isCellTextMatchRegex(final XWPFTableRow row,
                                               final int cellIndex,
                                               final String expectedRegex) {
        return isCellTextMatch(row, cellIndex, expectedRegex, XWPFTableCellUtil::isCellTextMatchRegex);
    }

    //TODO: remove
    public static boolean isChildUnitedRow(final XWPFTableRow row, final int contentCellIndex) {
        return isCellNullOrEmpty(row, contentCellIndex);
    }

    //TODO: test
    public static boolean isCellNullOrEmpty(final XWPFTableRow row, final int contentCellIndex) {
        final XWPFTableCell contentCell = row.getCell(contentCellIndex);
        return contentCell == null || isEmpty(contentCell);
    }

    private static <V> V extractCellValue(final XWPFTableRow row,
                                          final int cellIndex,
                                          final Function<XWPFTableCell, V> valueExtractor) {
        final XWPFTableCell cell = row.getCell(cellIndex);
        return valueExtractor.apply(cell);
    }

    private static boolean isCellTextMatch(final XWPFTableRow row,
                                           final int cellIndex,
                                           final String compared,
                                           final BiPredicate<XWPFTableCell, String> matchingPredicate) {
        final XWPFTableCell cell = row.getCell(cellIndex);
        return matchingPredicate.test(cell, compared);
    }

}
