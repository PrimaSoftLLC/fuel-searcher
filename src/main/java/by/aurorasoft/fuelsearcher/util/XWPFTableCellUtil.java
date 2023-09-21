package by.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import java.util.function.BiPredicate;

import static java.lang.Double.*;
import static java.util.stream.Collectors.joining;

@UtilityClass
public final class XWPFTableCellUtil {
    private static final String PARAGRAPH_TEXTS_SEPARATOR = " ";

    private static final String NOT_DEFINED_DOUBLE_VALUE_ALIAS = "-";
    public static final double NOT_DEFINED_DOUBLE = NaN;

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

    public static boolean isNotDefinedDouble(final double value) {
        return isNaN(value);
    }

    public static double extractDouble(final XWPFTableCell cell) {
        final String text = extractText(cell);
        return !text.equals(NOT_DEFINED_DOUBLE_VALUE_ALIAS) ? parseDouble(text) : NOT_DEFINED_DOUBLE;
    }

    public static boolean isCellTextEqualIgnoringWhitespacesAndCase(final XWPFTableCell cell, final String expected) {
        return isCellTextMatch(
                cell,
                expected,
                XWPFContentUtil::areEqualIgnoringWhitespacesAndCase
        );
    }

    public static boolean isCellTextMatchRegex(final XWPFTableCell cell, final String regex) {
        return isCellTextMatch(
                cell,
                regex,
                String::matches
        );
    }

    private static boolean isCellTextMatch(final XWPFTableCell cell,
                                           final String compared,
                                           final BiPredicate<String, String> matchingPredicate) {
        final String actual = extractText(cell);
        return matchingPredicate.test(actual, compared);
    }
}
