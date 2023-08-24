package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader;

import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.streamreader.CloseableXMLStreamReader;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

@Component
public final class FuelSearchersReader {
    private final Map<String, AbstractTableFuelSearchersReader<?>> readersByTagNames;

    public FuelSearchersReader(final List<AbstractTableFuelSearchersReader<?>> readers) {
        this.readersByTagNames = createReadersByTagNames(readers);
    }

    public List<AbstractTableFuelSearcher> read(final String filePath) {
        try (final CloseableXMLStreamReader streamReader = new CloseableXMLStreamReader(filePath)) {
            return this.read(streamReader);
        }
    }

    private static Map<String, AbstractTableFuelSearchersReader<?>> createReadersByTagNames(
            final List<AbstractTableFuelSearchersReader<?>> readers) {
        return readers.stream()
                .collect(
                        toMap(
                                AbstractTableFuelSearchersReader::getTagNameSearchers,
                                identity()
                        )
                );
    }

    private List<AbstractTableFuelSearcher> read(final CloseableXMLStreamReader streamReader) {
        final List<AbstractTableFuelSearcher> searchers = new ArrayList<>();
        while (!streamReader.isEndDocument()) {
            if (streamReader.next() == START_ELEMENT) {
                final String currentTagName = streamReader.findTagName();
                this.findReader(currentTagName)
                        .ifPresent(reader -> accumulateSearchers(reader, streamReader, searchers));
            }
        }
        return searchers;
    }

    private Optional<AbstractTableFuelSearchersReader<?>> findReader(final String tagName) {
        final AbstractTableFuelSearchersReader<?> searcher = this.readersByTagNames.get(tagName);
        return ofNullable(searcher);
    }

    private static void accumulateSearchers(final AbstractTableFuelSearchersReader<?> reader,
                                            final CloseableXMLStreamReader streamReader,
                                            final List<AbstractTableFuelSearcher> accumulator) {
        final List<? extends AbstractTableFuelSearcher> searchers = reader.read(streamReader);
        accumulator.addAll(searchers);
    }
}
