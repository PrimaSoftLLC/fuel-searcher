package by.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import static java.lang.Double.NaN;
import static java.lang.Double.parseDouble;
import static java.util.stream.Collectors.joining;

@UtilityClass
public final class XWPFTableCellUtil {
    private static final String PARAGRAPH_TEXTS_SEPARATOR = " ";
    private static final String NOT_DEFINED_DOUBLE_VALUE_ALIAS = "-";

    public static boolean isEmpty(final XWPFTableCell cell) {
        return cell.getParagraphs()
                .stream()
                .allMatch(XWPFParagraphUtil::isEmpty);
    }

    public static String extractText(final XWPFTableCell cell) {
        return cell.getParagraphs()
                .stream()
                .map(XWPFParagraphUtil::extractText)
                .filter(paragraphText -> !paragraphText.isEmpty())
                .collect(joining(PARAGRAPH_TEXTS_SEPARATOR));
    }

    public static double extractDouble(final XWPFTableCell cell) {
        final String text = extractText(cell);
        return !text.equals(NOT_DEFINED_DOUBLE_VALUE_ALIAS) ? parseDouble(text) : NaN;
    }

}
