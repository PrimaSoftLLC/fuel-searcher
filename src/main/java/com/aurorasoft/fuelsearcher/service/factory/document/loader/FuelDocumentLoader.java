package com.aurorasoft.fuelsearcher.service.factory.document.loader;

import com.aurorasoft.fuelsearcher.model.FuelDocument;
import com.aurorasoft.fuelsearcher.model.FuelTable;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.aurorasoft.fuelsearcher.util.StreamUtil.asStream;
import static com.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.*;
import static java.nio.file.Files.newInputStream;
import static java.util.Optional.empty;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.*;

@Component
public final class FuelDocumentLoader {

    public FuelDocument load(final String filePath) {
        try (final XWPFDocument document = loadXWPFDocument(filePath)) {
            return this.createFuelDocument(document);
        } catch (final IOException cause) {
            throw new FuelDocumentLoadingException(cause);
        }
    }

    private static XWPFDocument loadXWPFDocument(final String filePath)
            throws IOException {
        final Path path = Paths.get(filePath);
        final InputStream inputStream = newInputStream(path);
        return new XWPFDocument(inputStream);
    }

    private FuelDocument createFuelDocument(final XWPFDocument document) {
        return findElementsGroupedByTableNames(document)
                .entrySet()
                .stream()
                .map(FuelDocumentLoader::createTable)
                .collect(collectingAndThen(toList(), FuelDocument::new));
    }

    private static Map<String, List<IBodyElement>> findElementsGroupedByTableNames(final XWPFDocument document) {
        return asStream(new ElementBoundedToTableNameIterator(document))
                .collect(
                        groupingBy(
                                ElementBoundedToTableTitle::tableName,
                                mapping(ElementBoundedToTableTitle::element, toList())
                        )
                );
    }

    private static FuelTable createTable(final Entry<String, List<IBodyElement>> elementsByName) {
        final String name = elementsByName.getKey();
        final List<IBodyElement> elements = elementsByName.getValue();
        return new FuelTable(name, elements);
    }

    private static final class ElementBoundedToTableNameIterator implements Iterator<ElementBoundedToTableTitle> {
        private static final String TABLE_TITLE_REGEX = "(\\d+\\.[\\p{Z} ]+([А-Я\\s:,]+))";
        private static final Pattern TABLE_TITLE_PATTERN = compile(TABLE_TITLE_REGEX);
        private static final int TABLE_TITLE_GROUP_NUMBER = 1;
        private static final int TABLE_NAME_GROUP_NUMBER = 2;

        private static final String TABLE_TITLE_WITH_OTHER_CONTENT_REGEX = TABLE_TITLE_REGEX + "\n(.*)";
        private static final Pattern TABLE_TITLE_WITH_OTHER_CONTENT_PATTERN = compile(TABLE_TITLE_WITH_OTHER_CONTENT_REGEX);
        private static final int OTHER_CONTENT_GROUP_NUMBER = 3;

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
            //copying to avoid ConcurrentModificationException
            final List<IBodyElement> copiedElements = new ArrayList<>(document.getBodyElements());
            return copiedElements.stream()
                    .flatMap(ElementBoundedToTableNameIterator::splitIfIsParagraphAndContainsTitleWithOtherContent)
                    .filter(element -> !isEmptyParagraph(element))
                    .iterator();
        }

        private static Stream<IBodyElement> splitIfIsParagraphAndContainsTitleWithOtherContent(final IBodyElement element) {
            if (!(element instanceof final XWPFParagraph paragraph)) {
                return Stream.of(element);
            }
            return splitIfContainsTitleWithOtherContent(paragraph);
        }

        private static Stream<IBodyElement> splitIfContainsTitleWithOtherContent(final XWPFParagraph paragraph) {
            final String paragraphText = paragraph.getText();
            final Matcher matcher = TABLE_TITLE_WITH_OTHER_CONTENT_PATTERN.matcher(paragraphText);
            if (!matcher.matches()) {
                return Stream.of(paragraph);
            }
            final XWPFDocument document = paragraph.getDocument();
            final XWPFParagraph paragraphWithTableTitle = createParagraphWithTableTitle(matcher, document);
            final XWPFParagraph paragraphWithOtherContent = createParagraphWithOtherContent(matcher, document);
            return Stream.of(paragraphWithTableTitle, paragraphWithOtherContent);
        }

        private static XWPFParagraph createParagraphWithTableTitle(final Matcher matcher, final XWPFDocument document) {
            return createParagraphByGroupContent(matcher, TABLE_TITLE_GROUP_NUMBER, document);
        }

        private static XWPFParagraph createParagraphWithOtherContent(final Matcher matcher, final XWPFDocument document) {
            return createParagraphByGroupContent(matcher, OTHER_CONTENT_GROUP_NUMBER, document);
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
            return extractTextLines(paragraph)
                    .map(ElementBoundedToTableNameIterator::extractTableNameIfItIsTitle)
                    .filter(Optional::isPresent)
                    .findFirst()
                    .orElseGet(Optional::empty);
        }

        private static Optional<String> extractTableNameIfItIsTitle(final String source) {
            final Matcher matcher = TABLE_TITLE_PATTERN.matcher(source);
            return matcher.matches() ? Optional.of(matcher.group(TABLE_NAME_GROUP_NUMBER)) : empty();
        }
    }

    private record ElementBoundedToTableTitle(String tableName, IBodyElement element) {
    }


    private static final class FuelDocumentLoadingException extends RuntimeException {

        @SuppressWarnings("unused")
        public FuelDocumentLoadingException() {

        }

        @SuppressWarnings("unused")
        public FuelDocumentLoadingException(final String description) {
            super(description);
        }

        public FuelDocumentLoadingException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public FuelDocumentLoadingException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
