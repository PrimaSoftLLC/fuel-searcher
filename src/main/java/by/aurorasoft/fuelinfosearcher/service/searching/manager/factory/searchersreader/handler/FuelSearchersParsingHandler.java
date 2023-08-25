package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler;

import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.fueltablesearcher.FuelTableSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.ConclusiveRowFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.FuelSpecificationPropertyExtractorDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.GroupRowFilterDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.IntermediateRowFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group.AbstractGroupRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.SimpleTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.SimpleTableFuelSearcher.SimpleTableFuelSearcherBuilder;
import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.String.copyValueOf;

public final class FuelSearchersParsingHandler extends DefaultHandler {
    private final FuelTableSearcher fuelTableSearcher;
    private final GroupRowFilterDictionary groupRowFilterDictionary;
    private final IntermediateRowFilterFactoryDictionary intermediateRowFilterFactoryDictionary;
    private final ConclusiveRowFilterFactoryDictionary conclusiveRowFilterFactoryDictionary;
    private final FuelSpecificationPropertyExtractorDictionary fuelSpecificationPropertyExtractorDictionary;

    private final FuelSearchersParsingContext context;

    public FuelSearchersParsingHandler(final FuelTableSearcher fuelTableSearcher,
                                       final GroupRowFilterDictionary groupRowFilterDictionary,
                                       final IntermediateRowFilterFactoryDictionary intermediateRowFilterFactoryDictionary,
                                       final ConclusiveRowFilterFactoryDictionary conclusiveRowFilterFactoryDictionary,
                                       final FuelSpecificationPropertyExtractorDictionary fuelSpecificationPropertyExtractorDictionary) {
        this.fuelTableSearcher = fuelTableSearcher;
        this.groupRowFilterDictionary = groupRowFilterDictionary;
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
            case "simple-fuel-table" -> this.context.buildSearcher();
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
        final AbstractGroupRowFilter filter = this.groupRowFilterDictionary.find(this.context.getLastContent()).orElseThrow();
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
        this.context.accumulateFuelHeaderCellValueExtractor(
                this.fuelSpecificationPropertyExtractorDictionary.find(this.context.getLastContent()).orElseThrow()
        );
    }

}
