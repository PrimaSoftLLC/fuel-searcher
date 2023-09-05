package by.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

@UtilityClass
public final class XWPFTableCellUtil {

    public static boolean isEmpty(final XWPFTableCell cell) {
        return cell.getParagraphs()
                .stream()
                .allMatch(XWPFParagraphUtil::isEmpty);
    }

}
