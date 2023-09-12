package by.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.function.BiPredicate;
import java.util.function.Function;

import static by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.isEmpty;

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
        return isCellTextMatch(row, cellIndex, expected, XWPFContentComparingUtil::areEqualIgnoringWhitespacesAndCase);
    }

    public static boolean isCellTextMatchRegex(final XWPFTableRow row,
                                               final int cellIndex,
                                               final String expectedRegex) {
        return isCellTextMatch(row, cellIndex, expectedRegex, String::matches);
    }

    public static boolean isChildUnitedRow(final XWPFTableRow row, final int cellIndexWithContent) {
        final XWPFTableCell cellWithContent = row.getCell(cellIndexWithContent);
        return cellWithContent == null || isEmpty(cellWithContent);
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
                                           final BiPredicate<String, String> matchingPredicate) {
        final String actual = extractCellText(row, cellIndex);
        return matchingPredicate.test(actual, compared);
    }

}
