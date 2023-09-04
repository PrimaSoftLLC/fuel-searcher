package by.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

@UtilityClass
public final class XWPFParagraphUtil {
    private static final String NEW_LINES_REGEX = "\n+";
    private static final String EMPTY_STRING = "";

    private static final String NBSP_SYMBOLS_REGEX = "\\p{Z}+";
    private static final String NBSP_SYMBOLS_IN_START_STRING_REGEX = "^" + NBSP_SYMBOLS_REGEX;
    private static final String NBSP_SYMBOLS_IN_END_STRING_REGEX = NBSP_SYMBOLS_REGEX + "$";

    public static boolean isEmptyParagraph(final IBodyElement element) {
        return isMatchingParagraph(element, XWPFParagraphUtil::isEmpty);
    }

    public static Stream<String> extractTextLines(final XWPFParagraph paragraph) {
        final String text = extractText(paragraph);
        final String[] lines = text.split(NEW_LINES_REGEX);
        return stream(lines);
    }

    public static String extractParagraphText(final IBodyElement element) {
        final XWPFParagraph paragraph = (XWPFParagraph) element;
        return paragraph.getText();
    }

    public static XWPFParagraph createParagraph(final String content, final XWPFDocument document) {
        final XWPFParagraph paragraph = document.createParagraph();
        final XWPFRun run = paragraph.createRun();
        run.setText(content);
        return paragraph;
    }

    public static XWPFParagraph createParagraphByGroupContent(final Matcher matcher,
                                                              final int groupNumber,
                                                              final XWPFDocument document) {
        final String content = matcher.group(groupNumber);
        return createParagraph(content, document);
    }

    public static void replaceText(final XWPFParagraph paragraph, final String replacement) {
        removeAllRuns(paragraph);
        final XWPFRun run = paragraph.createRun();
        run.setText(replacement, 0);
    }

    private static boolean isMatchingParagraph(final IBodyElement element, final Predicate<XWPFParagraph> predicate) {
        if (!(element instanceof final XWPFParagraph paragraph)) {
            return false;
        }
        return predicate.test(paragraph);
    }

    private static boolean isEmpty(final XWPFParagraph paragraph) {
        final String paragraphText = extractText(paragraph);
        return paragraphText.isBlank();
    }

    private static String extractText(final XWPFParagraph paragraph) {
        final String text = paragraph.getText();
        final String trimmedText = text.trim();
        return trimByNbspSymbol(trimmedText);
    }

    private static String trimByNbspSymbol(final String source) {
        return source
                .replaceAll(NBSP_SYMBOLS_IN_START_STRING_REGEX, EMPTY_STRING)
                .replaceAll(NBSP_SYMBOLS_IN_END_STRING_REGEX, EMPTY_STRING);
    }

    private static void removeAllRuns(final XWPFParagraph paragraph) {
        final List<XWPFRun> runs = paragraph.getRuns();
        range(0, runs.size()).forEach(i -> paragraph.removeRun(0));
    }
}
