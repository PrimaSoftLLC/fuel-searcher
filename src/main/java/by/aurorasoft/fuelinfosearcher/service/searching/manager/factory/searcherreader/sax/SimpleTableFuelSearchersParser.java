package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.sax;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searching.exception.FuelTableNotExistException;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.dictionary.ConclusiveRowFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.dictionary.FuelSpecificationPropertyExtractorDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.dictionary.GroupRowFilterDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.dictionary.IntermediateRowFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group.AbstractGroupRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.SimpleTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.SimpleTableFuelSearcher.SimpleTableFuelSearcherBuilder;
import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;
import static java.lang.String.copyValueOf;

public final class SimpleTableFuelSearchersParser extends DefaultHandler {
    private final FuelDocument fuelDocument;
    private final GroupRowFilterDictionary groupRowFilterDictionary;
    private final IntermediateRowFilterFactoryDictionary intermediateRowFilterFactoryDictionary;
    private final ConclusiveRowFilterFactoryDictionary conclusiveRowFilterFactoryDictionary;
    private final FuelSpecificationPropertyExtractorDictionary fuelSpecificationPropertyExtractorDictionary;

    @Getter
    private final List<SimpleTableFuelSearcher> searchers;
    private SimpleTableFuelSearcherBuilder fuelSearcherBuilder;
    private Attributes currentAttributes;
    private String currentText;

    public SimpleTableFuelSearchersParser(final FuelDocument fuelDocument,
                                          final GroupRowFilterDictionary groupRowFilterDictionary,
                                          final IntermediateRowFilterFactoryDictionary intermediateRowFilterFactoryDictionary,
                                          final ConclusiveRowFilterFactoryDictionary conclusiveRowFilterFactoryDictionary,
                                          final FuelSpecificationPropertyExtractorDictionary fuelSpecificationPropertyExtractorDictionary) {
        this.fuelDocument = fuelDocument;
        this.groupRowFilterDictionary = groupRowFilterDictionary;
        this.intermediateRowFilterFactoryDictionary = intermediateRowFilterFactoryDictionary;
        this.conclusiveRowFilterFactoryDictionary = conclusiveRowFilterFactoryDictionary;
        this.fuelSpecificationPropertyExtractorDictionary = fuelSpecificationPropertyExtractorDictionary;
        this.fuelSearcherBuilder = SimpleTableFuelSearcher.builder();
        this.searchers = new ArrayList<>();
    }

    @Override
    public void startDocument() {
        this.searchers.clear();
    }

    @Override
    public void startElement(final String uri,
                             final String localName,
                             final String qualifiedName,
                             final Attributes attributes) {
        //TODO: remove switch
        this.currentAttributes = attributes;
    }

    @Override
    public void endElement(final String uri, final String localName, final String qualifiedName) {
        //TODO: remove switch
        switch (qualifiedName) {
            case "fuel-table" -> this.buildSearcher();
            case "name" -> this.accumulateFuelTable();
            case "fuel-header" -> this.accumulateFuelHeader();
            case "filter-by-group" -> this.accumulateGroupRowFilter();
            case "filter-by" -> this.accumulateIntermediateFilter();
            case "final-filter-by" -> this.accumulateConclusiveFilter();
            case "fuel-header-cell-property" -> this.accumulateFuelHeaderValueExtractor();
        }
    }

    @Override
    public void characters(final char[] chars, final int start, final int length) {
        final String copy = copyValueOf(chars, start, length);
        this.currentText = copy.trim();
    }

    private void accumulateFuelTable() {
        final FuelTable fuelTable = this.findFuelTable();
        this.fuelSearcherBuilder.fuelTable(fuelTable);
    }

    private FuelTable findFuelTable() {
        return this.fuelDocument.getTables()
                .stream()
                .filter(table -> Objects.equals(table.getName(), this.currentText))
                .findFirst()
                .orElseThrow(
                        () -> new FuelTableNotExistException(
                                "Table '%s' doesn't exist".formatted(this.currentText)
                        )
                );
    }

    private void accumulateFuelHeader() {
        this.fuelSearcherBuilder.fuelHeader(this.currentText);
    }

    private void accumulateGroupRowFilter() {
        //TODO: throw exception
        final AbstractGroupRowFilter filter = this.groupRowFilterDictionary.find(this.currentText).orElseThrow();
        this.fuelSearcherBuilder.intermediateFilter(filter);
    }

    private void accumulateIntermediateFilter() {
        this.fuelSearcherBuilder.intermediateFilter(
                this.intermediateRowFilterFactoryDictionary.find(this.currentText)
                        .orElseThrow()
                        .apply(parseInt(this.currentAttributes.getValue("cell-index")))
        );
    }

    private void accumulateConclusiveFilter() {
        this.fuelSearcherBuilder.conclusiveFilter(this.conclusiveRowFilterFactoryDictionary.find(this.currentText)
                .orElseThrow().apply(parseInt(this.currentAttributes.getValue("cell-index"))));
    }

    private void accumulateFuelHeaderValueExtractor() {
        this.fuelSearcherBuilder.fuelHeaderCellValueExtractor(this.fuelSpecificationPropertyExtractorDictionary.find(this.currentText).orElseThrow());
    }

    private void buildSearcher() {
        final SimpleTableFuelSearcher fuelSearcher = this.fuelSearcherBuilder.build();
        this.searchers.add(fuelSearcher);
        this.fuelSearcherBuilder = SimpleTableFuelSearcher.builder();
    }
}
