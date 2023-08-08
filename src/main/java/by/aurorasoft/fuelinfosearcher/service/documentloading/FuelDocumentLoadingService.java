package by.aurorasoft.fuelinfosearcher.service.documentloading;

import by.aurorasoft.fuelinfosearcher.service.documentloading.exception.FuelDocumentLoadingException;
import by.aurorasoft.fuelinfosearcher.util.XWPFParagraphUtil;
import lombok.Value;
import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.XWPFParagraphUtil.isEmptyParagraph;
import static by.aurorasoft.fuelinfosearcher.util.XWPFParagraphUtil.isMultilineParagraph;
import static java.nio.file.Files.newInputStream;
import static java.util.Arrays.stream;
import static java.util.Optional.empty;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.*;
import static by.aurorasoft.fuelinfosearcher.util.StreamUtil.asStream;

@Service
public final class FuelDocumentLoadingService {
    private static final String FILE_PATH = "postanovlenie128.2022.docx";

    public FuelDocument load() {
        try (final XWPFDocument document = new XWPFDocument(createFileInputStream())) {
            return this.load(document);
        } catch (final IOException cause) {
            throw new FuelDocumentLoadingException(cause);
        }
    }

    private static InputStream createFileInputStream()
            throws IOException {
        final Path filePath = Paths.get(FILE_PATH);
        return newInputStream(filePath);
    }

    private FuelDocument load(final XWPFDocument document) {
        return findElementsGroupedByTableNames(document)
                .entrySet()
                .stream()
                .map(FuelDocumentLoadingService::createTable)
                .collect(collectingAndThen(toList(), FuelDocument::new));
    }

    private static Map<String, List<IBodyElement>> findElementsGroupedByTableNames(final XWPFDocument document) {
        return asStream(new ElementBoundedToTableNameIterator(document))
                .collect(
                        groupingBy(
                                ElementBoundedToTableTitle::getTableName,
                                mapping(ElementBoundedToTableTitle::getElement, toList())
                        )
                );
    }

    private static FuelTable createTable(final Entry<String, List<IBodyElement>> elementsByName) {
        final String name = elementsByName.getKey();
        final List<IBodyElement> elements = elementsByName.getValue();
        return new FuelTable(name, elements);
    }

    //TODO: put methods in XWPFParagraphUtil
    private static final class ElementBoundedToTableNameIterator implements Iterator<ElementBoundedToTableTitle> {
        private static final String TABLE_TITLE_REGEX = "\\d+\\.\\p{Z}([А-Я\\s:,]+)";
        private static final Pattern TABLE_TITLE_PATTERN = compile(TABLE_TITLE_REGEX);
        private static final int TABLE_NAME_GROUP_NUMBER = 1;
        private static final String NEW_LINE = "\n";

        private final Iterator<IBodyElement> elementIterator;
        private String currentTableName;

        public ElementBoundedToTableNameIterator(final XWPFDocument document) {
            this.elementIterator = createElementIterator(document);
            this.iterateToFirstTableTitle();
        }

        @Override
        public boolean hasNext() {
            return this.elementIterator.hasNext();
        }

        @Override
        public ElementBoundedToTableTitle next() {
            final IBodyElement currentElement = this.iterateToNextNotTitleElement();
            return new ElementBoundedToTableTitle(this.currentTableName, currentElement);
        }

        private static Iterator<IBodyElement> createElementIterator(final XWPFDocument document) {
            return extractElementsSplittingMultilineParagraphs(document)
                    .filter(element -> !isEmptyParagraph(element))
                    .iterator();
        }

        private static Stream<IBodyElement> extractElementsSplittingMultilineParagraphs(final XWPFDocument document) {
            //coping to avoid ConcurrentModificationException
            final List<IBodyElement> copiedElements = new ArrayList<>(document.getBodyElements());
            return copiedElements.stream()
                    .flatMap(element -> splitMultilineParagraph(element, document));
        }

        private static Stream<IBodyElement> splitMultilineParagraph(final IBodyElement element, final XWPFDocument document) {
            if (!isMultilineParagraph(element)) {
                return Stream.of(element);
            }
            final XWPFParagraph paragraph = (XWPFParagraph) element;
            final String[] paragraphLines = paragraph.getText().split("\n");
            return stream(paragraphLines).map(line -> createParagraph(line, document));
        }

        private static XWPFParagraph createParagraph(final String content, final XWPFDocument document) {
            final XWPFParagraph paragraph = document.createParagraph();
            paragraph.createRun().setText(content);
            return paragraph;
        }

        private void iterateToFirstTableTitle() {
            Optional<String> optionalCurrentTableTitle;
            do {
                final IBodyElement currentElement = this.elementIterator.next();
                optionalCurrentTableTitle = extractTableNameIfContain(currentElement);
            } while (optionalCurrentTableTitle.isEmpty());
            this.currentTableName = optionalCurrentTableTitle.get();
        }

        private IBodyElement iterateToNextNotTitleElement() {
            final IBodyElement currentElement = this.elementIterator.next();
            final Optional<String> optionalTableName = extractTableNameIfContain(currentElement);
            return optionalTableName.map(newTableName -> {
                this.currentTableName = newTableName;
                return this.elementIterator.next();
            }).orElse(currentElement);
        }

        private static Optional<String> extractTableNameIfContain(final IBodyElement element) {
            return (element instanceof final XWPFParagraph paragraph) ? extractTableNameIfContain(paragraph) : empty();
        }

        private static Optional<String> extractTableNameIfContain(final XWPFParagraph paragraph) {
            return findParagraphLinesAsStream(paragraph)
                    .map(ElementBoundedToTableNameIterator::extractTableNameIfItIsTitle)
                    .filter(Optional::isPresent)
                    .findFirst()
                    .orElseGet(Optional::empty);
        }

        private static Stream<String> findParagraphLinesAsStream(final XWPFParagraph paragraph) {
            final String text = paragraph.getText();
            final String[] lines = text.split(NEW_LINE);
            return stream(lines);
        }

        private static Optional<String> extractTableNameIfItIsTitle(final String source) {
            final Matcher matcher = TABLE_TITLE_PATTERN.matcher(source);
            return matcher.matches() ? Optional.of(matcher.group(TABLE_NAME_GROUP_NUMBER)) : empty();
        }
    }

    @Value
    private static class ElementBoundedToTableTitle {
        String tableName;
        IBodyElement element;
    }
}
