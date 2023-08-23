package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader;

import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.streamreader.CloseableXMLStreamReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public final class FuelSearchersReader {
    private final List<AbstractTableFuelSearchersReader<?>> readers;

    public List<AbstractTableFuelSearcher> read(final String filePath) {
        try (final CloseableXMLStreamReader streamReader = new CloseableXMLStreamReader(filePath)) {
            final List<AbstractTableFuelSearcher> searchers = new ArrayList<>();
            while (streamReader.hasNext()) {
                this.readSearchersIfPossible(streamReader, searchers);
            }
            return searchers;
        }
    }

    private void readSearchersIfPossible(final CloseableXMLStreamReader streamReader,
                                         final List<AbstractTableFuelSearcher> searcherAccumulator) {
        this.findReaderAbleToStartReading(streamReader)
                .ifPresent(
                        reader -> searcherAccumulator.addAll(
                                reader.read(streamReader)
                        )
                );
    }

    private Optional<AbstractTableFuelSearchersReader<?>> findReaderAbleToStartReading(
            final CloseableXMLStreamReader streamReader) {
        return this.readers.stream()
                .filter(reader -> reader.isAbleToStartReading(streamReader))
                .findFirst();
    }
}
