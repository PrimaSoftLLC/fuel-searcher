package com.aurorasoft.fuelsearcher.service.searchersparser.handler;

import com.aurorasoft.fuelsearcher.model.FuelTable;
import com.aurorasoft.fuelsearcher.model.SubTableTitleMetadata;
import com.aurorasoft.fuelsearcher.model.SubTableTitleMetadata.SubTableTitleMetadataBuilder;
import com.aurorasoft.fuelsearcher.service.filter.conclusive.FinalFilter;
import com.aurorasoft.fuelsearcher.service.filter.interim.InterimFilter;
import com.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import com.aurorasoft.fuelsearcher.service.builder.BuilderRequiringAllProperties;
import com.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher;
import com.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher.CompositeSearcherBuilder;
import com.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import com.aurorasoft.fuelsearcher.service.searcher.FuelSearcher.SearcherBuilder;
import com.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher;
import com.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher.SimpleSearcherBuilder;
import lombok.Getter;
import lombok.Setter;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static lombok.AccessLevel.PRIVATE;

public final class SearchersParsingContext {
    private final List<FuelSearcher> searchers;

    @Setter(value = PRIVATE)
    @Getter(value = PRIVATE)
    private SimpleSearcherBuilder simpleSearcherBuilder;

    @Setter(value = PRIVATE)
    @Getter(value = PRIVATE)
    private CompositeSearcherBuilder compositeSearcherBuilder;

    @Setter(value = PRIVATE)
    @Getter(value = PRIVATE)
    private SubTableTitleMetadataBuilder subTableTitleMetadataBuilder;

    @Setter
    @Getter
    private String lastContent;

    @Setter
    @Getter
    private Attributes lastAttributes;

    public SearchersParsingContext() {
        this.searchers = new ArrayList<>();
    }

    public void startParsingSimpleSearcher() {
        this.simpleSearcherBuilder = SimpleFuelSearcher.builder();
    }

    public void startParsingCompositeSearcher() {
        this.compositeSearcherBuilder = CompositeFuelSearcher.builder();
        this.subTableTitleMetadataBuilder = SubTableTitleMetadata.builder();
    }

    public void accumulateFuelTable(final FuelTable table) {
        this.accumulateComponentToCurrentSearcherBuilder(table, SearcherBuilder::table);
    }

    public void buildAndAccumulateSimpleSearcher() {
        this.buildAndAccumulateSearcher(this::getSimpleSearcherBuilder, this::setSimpleSearcherBuilder);
    }

    public void buildAndAccumulateCompositeSearcher() {
        this.buildAndAccumulateSubTableTitleMetadata();
        this.buildAndAccumulateSearcher(this::getCompositeSearcherBuilder, this::setCompositeSearcherBuilder);
    }

    public void accumulateFuelHeaderMetadata(final FuelHeaderMetadata metadata) {
        this.accumulateComponentToCurrentSearcherBuilder(metadata, SearcherBuilder::headerMetadata);
    }

    public void accumulateFilter(final InterimFilter filter) {
        this.accumulateComponentToCurrentSearcherBuilder(filter, SearcherBuilder::interimFilter);
    }

    public void accumulateFilter(final FinalFilter filter) {
        this.accumulateComponentToCurrentSearcherBuilder(filter, SearcherBuilder::finalFilter);
    }

    public void accumulateSubTableTitleTemplate(final String template) {
        this.subTableTitleMetadataBuilder.templateWithPropertyNames(template);
    }

    public void accumulateSubTableTitleTemplateArgumentExtractor(final SpecificationPropertyExtractor extractor) {
        this.subTableTitleMetadataBuilder.argumentExtractor(extractor);
    }

    public List<FuelSearcher> findParsedSearchers() {
        return this.searchers;
    }

    private <T> void accumulateComponentToCurrentSearcherBuilder(
            final T component,
            final BiConsumer<SearcherBuilder<?>, T> accumulatingOperation
    ) {
        final SearcherBuilder<?> currentBuilder = this.findCurrentSearcherBuilder();
        accumulatingOperation.accept(currentBuilder, component);
    }

    private SearcherBuilder<?> findCurrentSearcherBuilder() {
        if (this.simpleSearcherBuilder != null) {
            return this.simpleSearcherBuilder;
        } else if (this.compositeSearcherBuilder != null) {
            return this.compositeSearcherBuilder;
        } else {
            throw new IllegalStateException("There is no current initialized builder");
        }
    }

    private <S extends FuelSearcher, B extends SearcherBuilder<S>> void buildAndAccumulateSearcher(
            final Supplier<B> builderGetter,
            final Consumer<B> builderSetter
    ) {
        buildAndAccumulateComponent(builderGetter, this.searchers::add, builderSetter);
    }

    private void buildAndAccumulateSubTableTitleMetadata() {
        buildAndAccumulateComponent(
                this::getSubTableTitleMetadataBuilder,
                this.compositeSearcherBuilder::subTableTitleMetadata,
                this::setSubTableTitleMetadataBuilder
        );
    }

    private static <C, B extends BuilderRequiringAllProperties<C>> void buildAndAccumulateComponent(
            final Supplier<B> builderGetter,
            final Consumer<C> accumulatingOperation,
            final Consumer<B> builderSetter
    ) {
        final B builder = builderGetter.get();
        final C component = builder.build();
        accumulatingOperation.accept(component);
        builderSetter.accept(null);
    }
}
