package by.aurorasoft.fuelsearcher.service.searchersparser.handler;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import by.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;
import by.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.searchersparser.SearchersParsingResult;
import by.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher.CompositeSearcherBuilder;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher.SearcherBuilder;
import by.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher.SimpleSearcherBuilder;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator.SpecificationValidatorBuilder;
import lombok.Getter;
import lombok.Setter;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static lombok.AccessLevel.PRIVATE;

public final class SearchersParsingContext {

    private final List<FuelSearcher> searchers;

    private final List<SpecificationValidator> specificationValidators;

    @Setter(value = PRIVATE)
    @Getter(value = PRIVATE)
    private SimpleSearcherBuilder simpleSearcherBuilder;

    @Setter(value = PRIVATE)
    @Getter(value = PRIVATE)
    private CompositeSearcherBuilder compositeSearcherBuilder;

    private SpecificationValidatorBuilder specificationValidatorBuilder;

    @Setter
    @Getter
    private String lastContent;

    @Setter
    @Getter
    private Attributes lastAttributes;

    public SearchersParsingContext() {
        this.searchers = new ArrayList<>();
        this.specificationValidators = new ArrayList<>();
    }

    public void startParseSimpleSearcher() {
        this.startParseSearcher(
                SimpleFuelSearcher::builder,
                this::setSimpleSearcherBuilder
        );
    }

    public void startParseCompositeSearcher() {
        this.startParseSearcher(
                CompositeFuelSearcher::builder,
                this::setCompositeSearcherBuilder
        );
    }

    public void accumulateFuelTable(final FuelTable fuelTable) {
        final String tableName = fuelTable.getName();
        this.specificationValidatorBuilder.tableName(tableName);
        this.accumulateComponentToCurrentBuilder(
                fuelTable,
                SearcherBuilder::table
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
        this.specificationValidatorBuilder.requiredPropertyExtractor(metadata.getHeaderExtractor());
        this.accumulateComponentToCurrentBuilder(
                metadata,
                SearcherBuilder::headerMetadata
        );
    }

    public void accumulateFilter(final InterimFilter filter) {
        this.specificationValidatorBuilder.requiredPropertyExtractor(filter.getFiltrationValueExtractor());
        this.accumulateComponentToCurrentBuilder(
                filter,
                SearcherBuilder::interimFilter
        );
    }

    public void accumulateFilter(final FinalFilter filter) {
        this.specificationValidatorBuilder.requiredPropertyExtractor(filter.getFiltrationValueExtractor());
        this.accumulateComponentToCurrentBuilder(
                filter,
                SearcherBuilder::finalFilter
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
        this.specificationValidatorBuilder.requiredPropertyExtractor(extractor);
        accumulateComponent(
                this.compositeSearcherBuilder,
                extractor,
                CompositeSearcherBuilder::subTableTitleTemplateArgumentExtractor
        );
    }

    public SearchersParsingResult findResult() {
        return new SearchersParsingResult(this.searchers, this.specificationValidators);
    }

    private <B extends SearcherBuilder<?>> void startParseSearcher(final Supplier<B> builderSupplier,
                                                                   final Consumer<B> builderSetter) {
        final B searcherBuilder = builderSupplier.get();
        builderSetter.accept(searcherBuilder);
        this.specificationValidatorBuilder = SpecificationValidator.builder();
    }

    private <T> void accumulateComponentToCurrentBuilder(final T component,
                                                         final BiConsumer<SearcherBuilder<?>, T> accumulatingOperation) {
        final SearcherBuilder<?> currentBuilder = this.findCurrentBuilder();
        accumulateComponent(currentBuilder, component, accumulatingOperation);
    }

    private static <T, B extends SearcherBuilder<?>> void accumulateComponent(final B builder,
                                                                              final T component,
                                                                              final BiConsumer<B, T> accumulatingOperation) {
        checkIfBuilderInitialized(builder);
        accumulatingOperation.accept(builder, component);
    }

    private SearcherBuilder<?> findCurrentBuilder() {
        if (this.simpleSearcherBuilder != null) {
            return this.simpleSearcherBuilder;
        } else {
            return this.compositeSearcherBuilder;
        }
    }

    private static void checkIfBuilderInitialized(final SearcherBuilder<?> builder) {
        if (builder == null) {
            throw new IllegalStateException("Builder isn't initialized");
        }
    }

    private <B extends SearcherBuilder<?>> void buildSearcher(final Function<SearchersParsingContext, B> builderGetter,
                                                              final BiConsumer<SearchersParsingContext, B> builderSetter) {
        final SpecificationValidator validator = this.specificationValidatorBuilder.build();
        this.specificationValidators.add(validator);
        this.specificationValidatorBuilder = null;

        final B builder = builderGetter.apply(this);
        checkIfBuilderInitialized(builder);
        final FuelSearcher searcher = builder.build();
        this.searchers.add(searcher);
        builderSetter.accept(this, null);
    }
}
