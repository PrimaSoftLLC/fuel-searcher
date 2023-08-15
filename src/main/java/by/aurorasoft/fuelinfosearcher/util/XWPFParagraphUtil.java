package by.aurorasoft.fuelinfosearcher.util;

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

    public static Stream<IBodyElement> splitIfMultilineParagraph(final IBodyElement element) {
        return isMultilineParagraph(element) ? splitMultilineParagraph(element) : Stream.of(element);
    }

    public static Stream<String> extractParagraphLines(final XWPFParagraph paragraph) {
        final String text = paragraph.getText();
        final String[] lines = text.split(NEW_LINE);
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
        final List<XWPFRun> runs = paragraph.getRuns();
        final XWPFRun firstRun = runs.get(0);
        firstRun.setText(replacement, 0);
        removeAllRunsExceptFirst(paragraph);
    }

    private static boolean isMultilineParagraph(final XWPFParagraph paragraph) {
        return extractParagraphLines(paragraph).count() > 1;
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

    private static Stream<IBodyElement> splitMultilineParagraph(final IBodyElement element) {
        final XWPFParagraph paragraph = (XWPFParagraph) element;
        final XWPFDocument document = paragraph.getDocument();
        return extractParagraphLines(paragraph).map(line -> createParagraph(line, document));
    }

    private static void removeAllRunsExceptFirst(final XWPFParagraph paragraph) {
        final List<XWPFRun> runs = paragraph.getRuns();
        range(1, runs.size()).forEach(paragraph::removeRun);
    }
}
