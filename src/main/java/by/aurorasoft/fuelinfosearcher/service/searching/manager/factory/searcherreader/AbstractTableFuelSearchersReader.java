package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader;

import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.streamreader.CloseableXMLStreamReader;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTableFuelSearchersReader<S extends AbstractTableFuelSearcher> {
    private static final String TAG_NAME_SEARCHER = "fuel-table";

    private final String tagNameSearchers;

    public AbstractTableFuelSearchersReader(final String tagNameSearchers) {
        this.tagNameSearchers = tagNameSearchers;
    }

    public final String getTagNameSearchers() {
        return this.tagNameSearchers;
    }

    public final List<S> read(final CloseableXMLStreamReader streamReader) {
        final List<S> searchers = new ArrayList<>();
        while (streamReader.isStartTag(TAG_NAME_SEARCHER)) {
            final S searcher = this.readSearcher(streamReader);
            searchers.add(searcher);
        }
        return searchers;
    }

    protected abstract S readSearcher(final CloseableXMLStreamReader streamReader);
}
