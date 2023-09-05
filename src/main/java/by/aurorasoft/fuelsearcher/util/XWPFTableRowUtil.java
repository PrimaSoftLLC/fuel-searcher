package by.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.function.Function;

import static by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.extractText;
import static java.lang.Double.NaN;
import static java.lang.Double.parseDouble;

@UtilityClass
public final class XWPFTableRowUtil {
    private static final String NOT_DEFINED_DOUBLE_VALUE_ALIAS = "-";

    private static double parseDoubleValue(final String source) {
        return !source.equals(NOT_DEFINED_DOUBLE_VALUE_ALIAS) ? parseDouble(source) : NaN;
    }

    private static <V> V extractCellValue(final XWPFTableRow row,
                                          final int cellIndex,
                                          final Function<String, V> valueParser) {
        final String text = extractCellText(row, cellIndex);
        return valueParser.apply(text);
    }

    private static String extractCellText(final XWPFTableRow row, final int cellIndex) {
        final XWPFTableCell cell = row.getCell(cellIndex);
        return extractText(cell);
    }

}
