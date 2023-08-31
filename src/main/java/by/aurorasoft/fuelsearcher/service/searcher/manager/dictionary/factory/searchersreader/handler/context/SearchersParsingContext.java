package by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.context;

import by.aurorasoft.fuelsearcher.model.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import by.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher.CompositeSearcherBuilder;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher.FuelSearcherBuilder;
import by.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher.SimpleSearcherBuilder;
import lombok.Getter;
import lombok.Setter;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static lombok.AccessLevel.PRIVATE;

public final class SearchersParsingContext {

    @Getter
    private final List<FuelSearcher> parsedSearchers;

    @Setter(value = PRIVATE)
    @Getter(value = PRIVATE)
    private SimpleSearcherBuilder simpleSearcherBuilder;

    @Setter(value = PRIVATE)
    @Getter(value = PRIVATE)
    private CompositeSearcherBuilder compositeSearcherBuilder;

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
        this.simpleSearcherBuilder = SimpleFuelSearcher.builder();
    }

    public void startBuildCompositeSearcher() {
        this.compositeSearcherBuilder = CompositeFuelSearcher.builder();
    }

    public void accumulateFuelTable(final FuelTable fuelTable) {
        this.accumulateComponentToCurrentBuilder(
                fuelTable,
                FuelSearcherBuilder::fuelTable
        );
    }

    public void buildSimpleSearcher() {
        this.buildSearcher(
                SearchersParsingContext::getSimpleSearcherBuilder,
                SearchersParsingContext::setSimpleSearcherBuilder
        );
    }

    public void buildCompositeSearcher() {
        this.buildSearcher(
                SearchersParsingContext::getCompositeSearcherBuilder,
                SearchersParsingContext::setCompositeSearcherBuilder
        );
    }

    public void accumulateFuelHeaderMetadata(final FuelHeaderMetadata metadata) {
        this.accumulateComponentToCurrentBuilder(
                metadata,
                FuelSearcherBuilder::fuelHeaderMetadata
        );
    }

    public void accumulateFilter(final InterimFilter filter) {
        this.accumulateComponentToCurrentBuilder(
                filter,
                FuelSearcherBuilder::interimFilter
        );
    }

    public void accumulateFilter(final FinalFilter filter) {
        this.accumulateComponentToCurrentBuilder(
                filter,
                FuelSearcherBuilder::finalFilter
        );
    }

    public void accumulateSubTableTitleTemplate(final String template) {
        accumulateComponent(
                this.compositeSearcherBuilder,
                template,
                CompositeSearcherBuilder::subTableTitleTemplate
        );
    }

    public void accumulateSubTableTitleTemplateArgumentExtractor(final SpecificationPropertyExtractor extractor) {
        accumulateComponent(
                this.compositeSearcherBuilder,
                extractor,
                CompositeSearcherBuilder::subTableTitleTemplateArgumentExtractor
        );
    }

    private <T> void accumulateComponentToCurrentBuilder(final T component,
                                                         final BiConsumer<FuelSearcherBuilder<?>, T> accumulatingOperation) {
        final FuelSearcherBuilder<?> currentBuilder = this.findCurrentBuilder();
        accumulateComponent(currentBuilder, component, accumulatingOperation);
    }

    private static <T, B extends FuelSearcherBuilder<?>> void accumulateComponent(final B builder,
                                                                                  final T component,
                                                                                  final BiConsumer<B, T> accumulatingOperation) {
        checkIfBuilderInitialized(builder);
        accumulatingOperation.accept(builder, component);
    }

    private FuelSearcherBuilder<?> findCurrentBuilder() {
        if (this.simpleSearcherBuilder != null) {
            return this.simpleSearcherBuilder;
        } else {
            return this.compositeSearcherBuilder;
        }
    }

    private static void checkIfBuilderInitialized(final FuelSearcherBuilder<?> builder) {
        if (builder == null) {
            throw new IllegalStateException("Builder isn't initialized");
        }
    }

    private <B extends FuelSearcherBuilder<?>> void buildSearcher(final Function<SearchersParsingContext, B> builderGetter,
                                                                  final BiConsumer<SearchersParsingContext, B> builderSetter) {
        final B builder = builderGetter.apply(this);
        checkIfBuilderInitialized(builder);
        final FuelSearcher searcher = builder.build();
        this.parsedSearchers.add(searcher);
        builderSetter.accept(this, null);
    }
}
