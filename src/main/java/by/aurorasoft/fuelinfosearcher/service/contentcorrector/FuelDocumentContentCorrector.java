package by.aurorasoft.fuelinfosearcher.service.contentcorrector;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.contentcorrector.component.AbstractContentFuelDocumentComponentCorrector;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static by.aurorasoft.fuelinfosearcher.util.XWPFParagraphUtil.replaceText;
import static by.aurorasoft.fuelinfosearcher.util.XWPFTableCellUtil.isEmpty;

@Service
public final class FuelDocumentContentCorrector {
    private final Function<String, String> contentCorrector;

    public FuelDocumentContentCorrector(final List<AbstractContentFuelDocumentComponentCorrector> componentCorrectors) {
        this.contentCorrector = createContentCorrector(componentCorrectors);
    }

    public void correct(final FuelDocument document) {
        document.getTables()
                .stream()
                .map(FuelTable::getElements)
                .flatMap(Collection::stream)
                .forEach(this::correctContent);
        document.getTables()
                .stream()
                .map(FuelTable::getElements)
                .flatMap(Collection::stream)
                .filter(XWPFTable.class::isInstance)
                .map(element -> (XWPFTable)element)
                .map(XWPFTable::getRows)
                .flatMap(Collection::stream)
                .map(XWPFTableRow::getTableCells)
                .flatMap(Collection::stream)
                .forEach(cell -> System.out.println(cell.getText()));
    }

    private static Function<String, String> createContentCorrector(final List<AbstractContentFuelDocumentComponentCorrector> componentCorrectors) {
        return componentCorrectors.stream()
                .map(componentCorrector -> (Function<String, String>) componentCorrector::correct)
                .reduce(Function::andThen)
                .orElseThrow(() -> new IllegalArgumentException("There are no component correctors"));
    }

    private void correctContent(final IBodyElement element) {
        if (element instanceof final XWPFTable table) {
            this.correctContent(table);
        } else if (element instanceof final XWPFParagraph paragraph) {
            this.correctContent(paragraph);
        } else {
            throw new IllegalArgumentException("Given element isn't table or paragraph");
        }
    }

    private void correctContent(final XWPFTable table) {
        table.getRows()
                .stream()
                .map(XWPFTableRow::getTableCells)
                .flatMap(Collection::stream)
                .filter(cell -> !isEmpty(cell))
                .forEach(this::correctContent);
    }

    private void correctContent(final XWPFTableCell cell) {
        cell
                .getParagraphs()
                .forEach(this::correctContent);
    }

    private void correctContent(final XWPFParagraph paragraph) {
        final String content = paragraph.getText();
        final String correctedContent = this.contentCorrector.apply(content);
        replaceText(paragraph, correctedContent);
    }
}
