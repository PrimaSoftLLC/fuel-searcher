package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context;

import by.aurorasoft.fuelinfosearcher.functionalinterface.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.model.FuelHeaderMetadata;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searcher.CompositeFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searcher.CompositeFuelSearcher.CompositeFuelSearcherBuilder;
import by.aurorasoft.fuelinfosearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searcher.FuelSearcher.FuelSearcherBuilder;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.conclusive.FinalFilter;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.AbstractInterimFilter;
import by.aurorasoft.fuelinfosearcher.service.searcher.SimpleFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searcher.SimpleFuelSearcher.SimpleFuelSearcherBuilder;
import lombok.Getter;
import lombok.Setter;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;

//TODO: refactor
public final class SearchersParsingContext {

    @Getter
    private final List<FuelSearcher> parsedSearchers;

    private SimpleFuelSearcherBuilder simpleFuelSearcherBuilder;

    private CompositeFuelSearcherBuilder compositeFuelSearcherBuilder;

    @Setter
    @Getter
    private String lastContent;

    @Setter
    @Getter
    private Attributes lastAttributes;

    public SearchersParsingContext() {
        this.parsedSearchers = new ArrayList<>();
    }

    public void startBuildSimpleSearcher() {
        this.simpleFuelSearcherBuilder = SimpleFuelSearcher.builder();
    }

    public void startBuildCompositeSearcher() {
        this.compositeFuelSearcherBuilder = CompositeFuelSearcher.builder();
    }

    public void accumulateFuelTable(final FuelTable fuelTable) {
        this.findCurrentBuilder().fuelTable(fuelTable);
    }

    public void buildSimpleSearcher() {
        final SimpleFuelSearcher searcher = this.simpleFuelSearcherBuilder.build();
        this.parsedSearchers.add(searcher);
        this.simpleFuelSearcherBuilder = null;
    }

    public void buildCompositeSearcher() {
        final CompositeFuelSearcher searcher = this.compositeFuelSearcherBuilder.build();
        this.parsedSearchers.add(searcher);
        this.compositeFuelSearcherBuilder = null;
    }

    public void accumulateFuelHeaderMetaData(final FuelHeaderMetadata metadata) {
        this.findCurrentBuilder().fuelHeaderMetadata(metadata);
    }

    public void accumulateFilter(final AbstractInterimFilter filter) {
        this.findCurrentBuilder().intermediateFilter(filter);
    }

    public void accumulateFilter(final FinalFilter filter) {
        this.findCurrentBuilder().conclusiveFilter(filter);
    }

    public void accumulateSubTableTitleTemplate(final String template) {
        this.compositeFuelSearcherBuilder.subTableTitleTemplate(template);
    }

    public void accumulateSubTableTitleTemplateArgumentExtractor(final SpecificationPropertyExtractor extractor) {
        this.compositeFuelSearcherBuilder.subTableTitleTemplateArgumentExtractor(extractor);
    }

    private FuelSearcherBuilder<?> findCurrentBuilder() {
        if (this.simpleFuelSearcherBuilder != null) {
            return this.simpleFuelSearcherBuilder;
        } else if (this.compositeFuelSearcherBuilder != null) {
            return this.compositeFuelSearcherBuilder;
        }
        throw new RuntimeException();
    }
}
