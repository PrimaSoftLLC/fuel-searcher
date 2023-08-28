package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler;

import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.FinalFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.interim.InterimFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.fueltablesearcher.FuelTableSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.SpecificationPropertyExtractorDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.group.GroupFilter;
import lombok.Builder;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.String.copyValueOf;

public final class FuelSearchersParsingHandler extends DefaultHandler {
    private final FuelTableSearcher fuelTableSearcher;
    private final InterimFilterFactoryDictionary intermediateRowFilterFactoryDictionary;
    private final FinalFilterFactoryDictionary conclusiveRowFilterFactoryDictionary;
    private final SpecificationPropertyExtractorDictionary fuelSpecificationPropertyExtractorDictionary;
    private final FuelSearchersParsingContext context;

    @Builder
    public FuelSearchersParsingHandler(final FuelTableSearcher fuelTableSearcher,
                                       final InterimFilterFactoryDictionary intermediateRowFilterFactoryDictionary,
                                       final FinalFilterFactoryDictionary conclusiveRowFilterFactoryDictionary,
                                       final SpecificationPropertyExtractorDictionary fuelSpecificationPropertyExtractorDictionary) {
        this.fuelTableSearcher = fuelTableSearcher;
        this.intermediateRowFilterFactoryDictionary = intermediateRowFilterFactoryDictionary;
        this.conclusiveRowFilterFactoryDictionary = conclusiveRowFilterFactoryDictionary;
        this.fuelSpecificationPropertyExtractorDictionary = fuelSpecificationPropertyExtractorDictionary;
        this.context = new FuelSearchersParsingContext();
    }

    public List<AbstractTableFuelSearcher> findParsedSearchers() {
        return this.context.getParsedSearchers();
    }

    @Override
    public void startElement(final String uri,
                             final String localName,
                             final String qualifiedName,
                             final Attributes attributes) {
        this.context.setLastAttributes(attributes);
    }

    @Override
    public void endElement(final String uri, final String localName, final String qualifiedName) {
        //TODO: remove switch
        switch (qualifiedName) {
            case "name" -> this.accumulateFuelTable();
            case "fuel-header" -> this.context.accumulateFuelHeader();
            case "filter-by-group" -> this.accumulateGroupRowFilter();
            case "filter-by" -> this.accumulateIntermediateFilter();
            case "final-filter-by" -> this.accumulateConclusiveFilter();
            case "fuel-header-cell-property" -> this.accumulateFuelHeaderValueExtractor();
            case "simple-fuel-table" -> this.context.buildSimpleSearcher();
        }
    }

    @Override
    public void characters(final char[] chars, final int start, final int length) {
        final String copy = copyValueOf(chars, start, length);
        final String trimmedCopy = copy.trim();
        this.context.setLastContent(trimmedCopy);
    }

    private void accumulateFuelTable() {
        final String lastContent = this.context.getLastContent();
        final FuelTable fuelTable = this.fuelTableSearcher.findFuelTable(lastContent);
        this.context.accumulateFuelTable(fuelTable);
    }

    private void accumulateGroupRowFilter() {
        //TODO: throw exception
        final GroupFilter filter = this.groupRowFilterDictionary.find(this.context.getLastContent()).orElseThrow();
        this.context.accumulateFilter(filter);
    }

    private void accumulateIntermediateFilter() {
        this.context.accumulateFilter(
                this.intermediateRowFilterFactoryDictionary.find(this.context.getLastContent())
                        .orElseThrow()
                        .apply(parseInt(this.context.getLastAttributes().getValue("cell-index")))
        );
    }

    private void accumulateConclusiveFilter() {
        this.context.accumulateFilter(this.conclusiveRowFilterFactoryDictionary.find(this.context.getLastContent())
                .orElseThrow().apply(parseInt(this.context.getLastAttributes().getValue("cell-index"))));
    }

    private void accumulateFuelHeaderValueExtractor() {
        this.context.accumulateFuelHeaderExtractor(
                this.fuelSpecificationPropertyExtractorDictionary.find(this.context.getLastContent()).orElseThrow()
        );
    }

}
