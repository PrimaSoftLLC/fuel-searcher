package by.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

//TODO: refactor
@UtilityClass
public final class XWPFTableCellUtil {

    public static boolean isEmpty(final XWPFTableCell cell) {
        //TODO: extract paragraphs and use XWPFParagraphUtil.isEmpty
        return cell.getText() == null || cell.getText().equals("");
    }

}
