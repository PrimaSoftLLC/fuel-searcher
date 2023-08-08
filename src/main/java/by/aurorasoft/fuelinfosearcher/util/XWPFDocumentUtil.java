package by.aurorasoft.fuelinfosearcher.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@UtilityClass
public final class XWPFDocumentUtil {

    public static Stream<IBodyElement> extractElementsSplittingMultilineParagraphs(final XWPFDocument document) {
        final List<IBodyElement> copiedElements = new ArrayList<>(document.getBodyElements());
        return copiedElements.stream().flatMap(XWPFParagraphUtil::splitIfMultilineParagraph);
    }

}
