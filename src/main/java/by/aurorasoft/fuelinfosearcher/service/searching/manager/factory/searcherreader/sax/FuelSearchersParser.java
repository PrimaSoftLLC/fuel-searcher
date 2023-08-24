package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.sax;

import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain.FilterChainBuilder;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.SimpleTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.SimpleTableFuelSearcher.SimpleTableFuelSearcherBuilder;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.copyValueOf;

public final class FuelSearchersParser extends DefaultHandler {
    private final List<SimpleTableFuelSearcher> parsedSearchers = new ArrayList<>();
    private FuelSearcherParsingContext searcherParsingContext;
    private String currentText;

    @Override
    public void startDocument() {
        this.parsedSearchers.clear();
    }

    @Override
    public void startElement(final String uri,
                             final String localName,
                             final String qualifiedName,
                             final Attributes attributes) {
        switch (qualifiedName) {
            case "name" -> this.accumulateFuelTable();
            case "fuel-header" -> this.accumulateFuelHeader();
            case "filter-by-group" -> this.accumulateGroupRowFilter();
            case "filter-by" -> this.accumulateIntermediateFilter();
            case "final-filter-by" -> this.accumulateConclusiveFilter();
            case "fuel-header-cell-property" -> this.accumulateFuelHeaderValueExtractor();
        }
    }

    @Override
    public void endElement(final String uri, final String localName, final String qualifiedName) {
        switch (qualifiedName) {
            case "fuel-table" -> this.searcherParsingContext.reset();
        }
    }

    @Override
    public void characters(final char[] chars, final int start, final int length) {
        this.currentText = copyValueOf(chars, start, length).trim();
    }

    private void accumulateFuelTable() {

    }

    private void accumulateFuelHeader() {

    }

    private void accumulateGroupRowFilter() {

    }

    private void accumulateIntermediateFilter() {

    }

    private void accumulateConclusiveFilter() {

    }

    private void accumulateFuelHeaderValueExtractor() {

    }

    private static class FuelSearcherParsingContext {
        private final List<String> parsedFuelHeaders = new ArrayList<>();
        private SimpleTableFuelSearcherBuilder builder = SimpleTableFuelSearcher.builder();
        private FilterChainBuilder filterChainBuilder = RowFilterChain.builder();

        public void reset() {

        }
    }
}
