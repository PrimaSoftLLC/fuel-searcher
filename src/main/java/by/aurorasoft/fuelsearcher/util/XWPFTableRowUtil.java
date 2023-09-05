package by.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.function.BiPredicate;
import java.util.function.Function;

@UtilityClass
public final class XWPFTableRowUtil {

    public static double extractCellDoubleValue(final XWPFTableRow row, final int cellIndex) {
        return extractCellValue(row, cellIndex, XWPFTableCellUtil::extractDouble);
    }

    public static String extractCellText(final XWPFTableRow row, final int cellIndex) {
        return extractCellValue(row, cellIndex, XWPFTableCellUtil::extractText);
    }

    public static boolean isCellContentEqual(final XWPFTableRow row, final int cellIndex, final String expected) {
        return isCellContentMatch(row, cellIndex, expected, String::equalsIgnoreCase);
    }

    public static boolean isCellContentMatchRegex(final XWPFTableRow row,
                                                  final int cellIndex,
                                                  final String expectedRegex) {
        return isCellContentMatch(row, cellIndex, expectedRegex, String::matches);
    }

    private static <V> V extractCellValue(final XWPFTableRow row,
                                          final int cellIndex,
                                          final Function<XWPFTableCell, V> valueExtractor) {
        final XWPFTableCell cell = row.getCell(cellIndex);
        return valueExtractor.apply(cell);
    }

    private static boolean isCellContentMatch(final XWPFTableRow row,
                                              final int cellIndex,
                                              final String compared,
                                              final BiPredicate<String, String> matchingPredicate) {
        final String actual = extractCellText(row, cellIndex);
        return matchingPredicate.test(actual, compared);
    }

}
