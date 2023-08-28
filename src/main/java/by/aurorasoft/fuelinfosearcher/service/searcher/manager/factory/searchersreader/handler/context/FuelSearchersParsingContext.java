package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context;

import by.aurorasoft.fuelinfosearcher.functionalinterface.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.conclusive.FinalFilter;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.AbstractInterimFilter;
import by.aurorasoft.fuelinfosearcher.service.searcher.SimpleFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searcher.SimpleFuelSearcher.SimpleFuelSearcherBuilder;
import lombok.Getter;
import lombok.Setter;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;

public final class FuelSearchersParsingContext {

    @Getter
    private final List<FuelSearcher> parsedSearchers;

    private SimpleFuelSearcherBuilder fuelSearcherBuilder;

    @Setter
    @Getter
    private String lastContent;

    @Setter
    @Getter
    private Attributes lastAttributes;

    public FuelSearchersParsingContext() {
        this.parsedSearchers = new ArrayList<>();
        this.fuelSearcherBuilder = SimpleFuelSearcher.builder();
    }

    public void accumulateFuelTable(final FuelTable fuelTable) {
        this.fuelSearcherBuilder.fuelTable(fuelTable);
    }

    public void buildSimpleSearcher() {
        final SimpleFuelSearcher searcher = this.fuelSearcherBuilder.build();
        this.parsedSearchers.add(searcher);
        this.fuelSearcherBuilder = SimpleFuelSearcher.builder();
    }

    public void accumulateFuelHeader() {
        this.fuelSearcherBuilder.fuelHeader(this.lastContent);
    }

    public void accumulateFilter(final AbstractInterimFilter filter) {
        this.fuelSearcherBuilder.intermediateFilter(filter);
    }

    public void accumulateFilter(final FinalFilter filter) {
        this.fuelSearcherBuilder.conclusiveFilter(filter);
    }

    public void accumulateFuelHeaderExtractor(final SpecificationPropertyExtractor extractor) {
        this.fuelSearcherBuilder.fuelHeaderCellValueExtractor(extractor);
    }
}
