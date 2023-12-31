package com.aurorasoft.fuelsearcher.service.factory.document.corrector;

import com.aurorasoft.fuelsearcher.model.FuelDocument;
import com.aurorasoft.fuelsearcher.model.FuelTable;
import com.aurorasoft.fuelsearcher.service.factory.document.corrector.paragraph.ParagraphCorrector;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static com.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.isEmpty;

@Component
@RequiredArgsConstructor
public final class FuelDocumentCorrector {
    private final List<ParagraphCorrector> correctors;

    public void correct(final FuelDocument document) {
        document.tables()
                .stream()
                .map(FuelTable::elements)
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
