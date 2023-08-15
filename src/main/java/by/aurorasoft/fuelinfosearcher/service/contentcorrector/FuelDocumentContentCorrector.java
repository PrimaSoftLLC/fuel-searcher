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
            throw new IllegalArgumentException("Given element isn't table and paragraph.");
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
        correctContent(
                cell,
                XWPFTableCell::getText,
                XWPFTableCell::setText
        );
    }

    private void correctContent(final XWPFParagraph paragraph) {
        correctContent(
                paragraph,
                XWPFParagraph::getText,
                FuelDocumentContentCorrector::replaceText
        );
    }

    private <T> void correctContent(final T documentElement,
                                    final Function<T, String> contentExtractor,
                                    final BiConsumer<T, String> contentSetter) {
        final String content = contentExtractor.apply(documentElement);
        final StringBuilder contentBuilder = new StringBuilder(content);
        this.componentCorrectors.forEach(componentCorrector -> componentCorrector.correct(contentBuilder));
        final String correctedContent = contentBuilder.toString();
        contentSetter.accept(documentElement, correctedContent);
    }

    private static void replaceText(final XWPFParagraph paragraph, final String replacement) {
        final List<XWPFRun> runs = paragraph.getRuns();
        final XWPFRun firstRun = runs.get(0);
        firstRun.setText(replacement, 0);
        removeAllExceptFirst(runs);
    }

    private static void removeAllExceptFirst(final List<XWPFRun> runs) {
        range(1, runs.size()).forEach(runs::remove);
    }
}
