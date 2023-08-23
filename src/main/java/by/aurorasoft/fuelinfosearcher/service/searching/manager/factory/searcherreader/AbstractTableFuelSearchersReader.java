package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader;

import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.streamreader.CloseableXMLStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

public abstract class AbstractTableFuelSearchersReader<S extends AbstractTableFuelSearcher> {
    private static final String TAG_NAME_SEARCHER = "fuel-table";

    private final String tagNameSearchers;

    public AbstractTableFuelSearchersReader(final String tagNameSearchers) {
        this.tagNameSearchers = tagNameSearchers;
    }

    public final boolean isAbleToStartReading(final CloseableXMLStreamReader streamReader) {
        return this.isStartTagContainingSearchers(streamReader);
    }

    public final List<S> read(final CloseableXMLStreamReader streamReader) {
        final List<S> searchers = new ArrayList<>();
        while (!this.isEndTagContainingSearchers(streamReader)) {
            skipUntilNextSearcher(streamReader);
            final S searcher = this.readSearcher(streamReader);
            searchers.add(searcher);
        }
        return searchers;
    }

    protected abstract S readSearcher(final CloseableXMLStreamReader streamReader);

    private static void skipUntilNextSearcher(final CloseableXMLStreamReader streamReader) {
        while (!isStartTagContainingSearcher(streamReader)) {
            streamReader.next();
        }
    }

    //TODO: put in CloseableXMLStreamReader
    private boolean isStartTagContainingSearchers(final CloseableXMLStreamReader streamReader) {
        return streamReader.findEventType() == START_ELEMENT
                && Objects.equals(streamReader.findLocalName(), this.tagNameSearchers);
    }

    //TODO: put in CloseableXMLStreamReader
    private boolean isEndTagContainingSearchers(final CloseableXMLStreamReader streamReader) {
        return streamReader.findEventType() == END_ELEMENT
                && Objects.equals(streamReader.findLocalName(), this.tagNameSearchers);
    }

    //TODO: put in CloseableXMLStreamReader
    private static boolean isStartTagContainingSearcher(final CloseableXMLStreamReader streamReader) {
        return streamReader.findEventType() == START_ELEMENT
                && Objects.equals(streamReader.findLocalName(), TAG_NAME_SEARCHER);
    }
}
