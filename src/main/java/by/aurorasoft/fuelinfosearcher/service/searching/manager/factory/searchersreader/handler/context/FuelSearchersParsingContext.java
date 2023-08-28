package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.context;

import by.aurorasoft.fuelinfosearcher.functionalinterface.FuelSpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.AbstractConclusiveRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.AbstractInterimRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.SimpleTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.SimpleTableFuelSearcher.SimpleTableFuelSearcherBuilder;
import lombok.Getter;
import lombok.Setter;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;

public final class FuelSearchersParsingContext {

    @Getter
    private final List<AbstractTableFuelSearcher> parsedSearchers;

    private SimpleTableFuelSearcherBuilder fuelSearcherBuilder;

    @Setter
    @Getter
    private String lastContent;

    @Setter
    @Getter
    private Attributes lastAttributes;

    public FuelSearchersParsingContext() {
        this.parsedSearchers = new ArrayList<>();
    }

    public void accumulateFuelTable(final FuelTable fuelTable) {
        this.fuelSearcherBuilder.fuelTable(fuelTable);
    }

    public void buildSearcher() {
        final SimpleTableFuelSearcher searcher = this.fuelSearcherBuilder.build();
        this.parsedSearchers.add(searcher);
    }

    public void accumulateFuelHeader() {
        this.fuelSearcherBuilder.fuelHeader(this.lastContent);
    }

    public void accumulateFilter(final AbstractInterimRowFilter filter) {
        this.fuelSearcherBuilder.intermediateFilter(filter);
    }

    public void accumulateFilter(final AbstractConclusiveRowFilter filter) {
        this.fuelSearcherBuilder.conclusiveFilter(filter);
    }

    public void accumulateFuelHeaderCellValueExtractor(final FuelSpecificationPropertyExtractor extractor) {
        this.fuelSearcherBuilder.fuelHeaderCellValueExtractor(extractor);
    }
}
