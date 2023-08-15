package by.aurorasoft.fuelinfosearcher.service.contentcorrector;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.contentcorrector.component.AbstractContentFuelDocumentComponentCorrector;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static java.util.stream.IntStream.range;

//TODO: refactor
@Service
@RequiredArgsConstructor
public final class FuelDocumentContentCorrector {
    private final List<AbstractContentFuelDocumentComponentCorrector> componentCorrectors;

    public void correct(final FuelDocument document) {
        document.getTables()
                .stream()
                .map(FuelTable::getElements)
                .flatMap(Collection::stream)
                .forEach(this::correctContent);
    }

    private void correctContent(final IBodyElement element) {
        if (element instanceof final XWPFTable table) {
            this.correctContent(table);
        } else if (element instanceof final XWPFParagraph paragraph) {
            this.correctContent(paragraph);
        } else {
            throw new IllegalArgumentException("Given element isn't table or paragraph.");
        }
    }

    private void correctContent(final XWPFTable table) {
        table.getRows()
                .stream()
                .map(XWPFTableRow::getTableCells)
                .flatMap(Collection::stream)
                .forEach(this::correctContent);
    }

    private void correctContent(final XWPFTableCell cell) {
        this.correctContent(
                cell,
                XWPFTableCell::getText,
                XWPFTableCell::setText
        );
    }

    private void correctContent(final XWPFParagraph paragraph) {
        this.correctContent(
                paragraph,
                XWPFParagraph::getText,
                FuelDocumentContentCorrector::replaceText
        );
    }

    private <T> void correctContent(final T documentElement,
                                    final Function<T, String> contentExtractor,
                                    final BiConsumer<T, String> contentSetter) {
        final String content = contentExtractor.apply(documentElement);
        final String correctedContent = this.correctContent(content);
        contentSetter.accept(documentElement, correctedContent);
    }

    private String correctContent(String content) {
        for (final AbstractContentFuelDocumentComponentCorrector componentCorrector : this.componentCorrectors) {
            content = componentCorrector.correct(content);
        }
        return content;
    }

    private static void replaceText(final XWPFParagraph paragraph, final String replacement) {
        final List<XWPFRun> runs = paragraph.getRuns();
        final XWPFRun firstRun = runs.get(0);
        firstRun.setText(replacement, 0);
        removeAllRunsExceptFirst(paragraph);
    }

    private static void removeAllRunsExceptFirst(final XWPFParagraph paragraph) {
        final List<XWPFRun> runs = paragraph.getRuns();
        range(1, runs.size()).forEach(paragraph::removeRun);
    }
}
