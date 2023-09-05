package by.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import static java.util.stream.Collectors.joining;

@UtilityClass
public final class XWPFTableCellUtil {
    private static final String PARAGRAPH_TEXTS_SEPARATOR = " ";

    public static boolean isEmpty(final XWPFTableCell cell) {
        return cell.getParagraphs()
                .stream()
                .allMatch(XWPFParagraphUtil::isEmpty);
    }

    public static String extractText(final XWPFTableCell cell) {
        return cell.getParagraphs()
                .stream()
                .map(XWPFParagraphUtil::extractText)
                .collect(joining(PARAGRAPH_TEXTS_SEPARATOR));
    }

}
