package by.aurorasoft.fuelinfosearcher.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.function.Predicate;

@UtilityClass
public final class XWPFParagraphUtil {
    private static final String NEW_LINE = "\n";
    private static final String EMPTY_STRING = "";

    private static final String NBSP_SYMBOLS_REGEX = "\\p{Z}+";
    private static final String NBSP_SYMBOLS_IN_START_STRING_REGEX = "^" + NBSP_SYMBOLS_REGEX;
    private static final String NBSP_SYMBOLS_IN_END_STRING_REGEX = NBSP_SYMBOLS_REGEX + "$";

    public static boolean isMultilineParagraph(final IBodyElement element) {
        return isMatchingParagraph(element, XWPFParagraphUtil::isMultilineParagraph);
    }

    public static boolean isEmptyParagraph(final IBodyElement element) {
        return isMatchingParagraph(element, XWPFParagraphUtil::isEmptyParagraph);
    }

    private static boolean isMultilineParagraph(final XWPFParagraph paragraph) {
        final String paragraphText = paragraph.getText();
        final String[] lines = paragraphText.split(NEW_LINE);
        return lines.length > 1;
    }

    private static boolean isEmptyParagraph(final XWPFParagraph paragraph) {
        final String paragraphText = extractTextTrimmingNBSPSymbol(paragraph);
        return paragraphText.isBlank();
    }

    private static boolean isMatchingParagraph(final IBodyElement element, final Predicate<XWPFParagraph> predicate) {
        if (!(element instanceof final XWPFParagraph paragraph)) {
            return false;
        }
        return predicate.test(paragraph);
    }

    private static String extractTextTrimmingNBSPSymbol(final XWPFParagraph paragraph) {
        final String paragraphText = paragraph.getText();
        return paragraphText
                .replaceAll(NBSP_SYMBOLS_IN_START_STRING_REGEX, EMPTY_STRING)
                .replaceAll(NBSP_SYMBOLS_IN_END_STRING_REGEX, EMPTY_STRING);

    }
}
