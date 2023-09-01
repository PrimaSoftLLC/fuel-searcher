package by.aurorasoft.fuelsearcher.service.documentfactory.corrector;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.service.documentfactory.corrector.component.ContentParagraphCorrector;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.isEmpty;

@Component
@RequiredArgsConstructor
public final class FuelDocumentCorrector {
    private final List<ContentParagraphCorrector> correctors;

    public void correct(final FuelDocument document) {
        document.getTables()
                .stream()
                .map(FuelTable::getElements)
                .flatMap(Collection::stream)
                .flatMap(FuelDocumentCorrector::mapToParagraphStream)
                .forEach(this::correct);
    }

    private static Stream<XWPFParagraph> mapToParagraphStream(final IBodyElement element) {
        if (element instanceof final XWPFTable table) {
            return mapToParagraphStream(table);
        } else if (element instanceof final XWPFParagraph paragraph) {
            return mapToParagraphStream(paragraph);
        } else {
            throw new IllegalArgumentException("Given element isn't table or paragraph");
        }
    }

    private static Stream<XWPFParagraph> mapToParagraphStream(final XWPFTable table) {
        return table.getRows()
                .stream()
                .map(XWPFTableRow::getTableCells)
                .flatMap(Collection::stream)
                .filter(cell -> !isEmpty(cell))
                .map(XWPFTableCell::getParagraphs)
                .flatMap(Collection::stream);
    }

    private static Stream<XWPFParagraph> mapToParagraphStream(final XWPFParagraph paragraph) {
        return Stream.of(paragraph);
    }

    private void correct(final XWPFParagraph paragraph) {
        this.correctors.forEach(corrector -> corrector.correct(paragraph));
    }

}
