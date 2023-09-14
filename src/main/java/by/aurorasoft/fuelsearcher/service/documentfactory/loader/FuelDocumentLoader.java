package by.aurorasoft.fuelsearcher.service.documentfactory.loader;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.service.documentfactory.loader.iterator.ElementBoundedToTableNameIterator;
import by.aurorasoft.fuelsearcher.service.documentfactory.loader.model.ElementBoundedToTableTitle;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static by.aurorasoft.fuelsearcher.util.StreamUtil.asStream;
import static java.nio.file.Files.newInputStream;
import static java.util.stream.Collectors.*;

@Component
public final class FuelDocumentLoader {

    public FuelDocument load(final String filePath) {
        try (final XWPFDocument document = new XWPFDocument(createFileInputStream(filePath))) {
            return this.load(document);
        } catch (final IOException cause) {
            throw new FuelDocumentLoadingException(cause);
        }
    }

    private static InputStream createFileInputStream(final String filePath)
            throws IOException {
        final Path path = Paths.get(filePath);
        return newInputStream(path);
    }

    private FuelDocument load(final XWPFDocument document) {
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
