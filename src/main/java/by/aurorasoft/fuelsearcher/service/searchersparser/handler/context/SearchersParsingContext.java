package by.aurorasoft.fuelsearcher.service.searchersparser.handler.context;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata.TableMetadataBuilder;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import by.aurorasoft.fuelsearcher.model.SubTableTitleMetadata;
import by.aurorasoft.fuelsearcher.model.SubTableTitleMetadata.SubTableTitleMetadataBuilder;
import by.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import by.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;
import by.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.builder.BuilderRequiringAllProperties;
import by.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher.CompositeSearcherBuilder;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher.SearcherBuilder;
import by.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher.SimpleSearcherBuilder;
import by.aurorasoft.fuelsearcher.service.searchersparser.SearchersParsingResult;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher.PropertyMetadataSearchingManager;
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
    private final PropertyMetadataSearchingManager propertyMetadataSearchingManager;

    private final List<FuelSearcher> searchers;

    private final List<SpecificationValidator> specificationValidators;

    private final List<TableMetadata> tablesMetadata;

    @Setter(value = PRIVATE)
    @Getter(value = PRIVATE)
    private SimpleSearcherBuilder simpleSearcherBuilder;

    @Setter(value = PRIVATE)
    @Getter(value = PRIVATE)
    private CompositeSearcherBuilder compositeSearcherBuilder;

    @Setter(value = PRIVATE)
    @Getter(value = PRIVATE)
    private SubTableTitleMetadataBuilder subTableTitleMetadataBuilder;

    @Setter(value = PRIVATE)
    @Getter(value = PRIVATE)
    private SpecificationValidatorBuilder specificationValidatorBuilder;

    @Setter(value = PRIVATE)
    @Getter(value = PRIVATE)
    private TableMetadataBuilder tableMetadataBuilder;

    @Setter
    @Getter
    private String lastContent;

    @Setter
    @Getter
    private Attributes lastAttributes;

    public static SearchersParsingContext createContextNotCollectingMetadata() {
        return new SearchersParsingContext(null, null);
    }

    public static SearchersParsingContext createContextCollectingMetadata(
            final PropertyMetadataSearchingManager metadataSearchingManager
    ) {
        return new SearchersParsingContext(metadataSearchingManager, new ArrayList<>());
    }

    private SearchersParsingContext(final PropertyMetadataSearchingManager propertyMetadataSearchingManager,
                                    final List<TableMetadata> tablesMetadata) {
        this.propertyMetadataSearchingManager = propertyMetadataSearchingManager;
        this.searchers = new ArrayList<>();
        this.specificationValidators = new ArrayList<>();
        this.tablesMetadata = tablesMetadata;
    }

    public void startParseSimpleSearcher() {
        this.startParseSearcher(SimpleFuelSearcher::builder, this::setSimpleSearcherBuilder);
    }

    public void startParseCompositeSearcher() {
        this.startParseSearcher(CompositeFuelSearcher::builder, this::setCompositeSearcherBuilder);
        this.subTableTitleMetadataBuilder = this.createSubTableTitleMetadataBuilder();
    }

    public void accumulateFuelTable(final FuelTable fuelTable) {
        final String tableName = fuelTable.name();
        this.specificationValidatorBuilder.tableName(tableName);
        this.accumulateComponentIfMetadataCollectingRequired(
                this::getTableMetadataBuilder,
                tableName,
                TableMetadataBuilder::tableName
        );
        this.accumulateComponentToCurrentSearcherBuilder(fuelTable, SearcherBuilder::table);
    }

    public void buildSimpleSearcher() {
        this.buildAndAccumulateSearcher(this::getSimpleSearcherBuilder, this::setSimpleSearcherBuilder);
    }

    public void buildCompositeSearcher() {
        this.buildAndAccumulateSubTableTitleMetadata();
        this.buildAndAccumulateSearcher(this::getCompositeSearcherBuilder, this::setCompositeSearcherBuilder);
    }

    public void accumulateFuelHeaderMetadata(final FuelHeaderMetadata metadata) {
        this.accumulateComponentWithRequiredPropertyExtractorToCurrentSearcherBuilder(
                metadata,
                FuelHeaderMetadata::getHeaderExtractor,
                SearcherBuilder::headerMetadata
        );
        this.accumulatePropertyMetadataIfMetadataCollectingRequired(metadata);
    }

    public void accumulateFilter(final InterimFilter filter) {
        this.accumulateComponentWithRequiredPropertyExtractorToCurrentSearcherBuilder(
                filter,
                InterimFilter::getFiltrationValueExtractor,
                SearcherBuilder::interimFilter
        );
        this.accumulatePropertyMetadataIfMetadataCollectingRequired(filter);
    }

    public void accumulateFilter(final FinalFilter filter) {
        this.accumulateComponentWithRequiredPropertyExtractorToCurrentSearcherBuilder(
                filter,
                FinalFilter::getFiltrationValueExtractor,
                SearcherBuilder::finalFilter
        );
        this.accumulatePropertyMetadataIfMetadataCollectingRequired(filter);
    }

    public void accumulateSubTableTitleTemplate(final String template) {
        this.subTableTitleMetadataBuilder.templateWithPropertyNames(template);
    }

    public void accumulateSubTableTitleTemplateArgumentExtractor(final SpecificationPropertyExtractor extractor) {
        this.specificationValidatorBuilder.requiredPropertyExtractor(extractor);
        this.subTableTitleMetadataBuilder.argumentExtractor(extractor);
    }

    public SearchersParsingResult findResult() {
        return new SearchersParsingResult(this.searchers, this.specificationValidators, this.tablesMetadata);
    }

    private <B extends SearcherBuilder<?>> void startParseSearcher(final Supplier<B> builderSupplier,
                                                                   final Consumer<B> builderSetter) {
        final B searcherBuilder = builderSupplier.get();
        builderSetter.accept(searcherBuilder);
        this.specificationValidatorBuilder = SpecificationValidator.builder();
        this.tableMetadataBuilder = this.createTableMetadataBuilder();
    }

    private TableMetadataBuilder createTableMetadataBuilder() {
        return this.createBuilderIfMetadataCollectingRequired(TableMetadata::builder);
    }

    private SubTableTitleMetadataBuilder createSubTableTitleMetadataBuilder() {
        return this.createBuilderIfMetadataCollectingRequired(SubTableTitleMetadata::builder);
    }

    private <B extends BuilderRequiringAllProperties<?>> B createBuilderIfMetadataCollectingRequired(
            final Supplier<B> builderSupplier
    ) {
        return this.isMetadataCollectingRequired() ? builderSupplier.get() : null;
    }

    private boolean isMetadataCollectingRequired() {
        return this.propertyMetadataSearchingManager != null;
    }

    private <B extends BuilderRequiringAllProperties<?>, C> void accumulateComponentIfMetadataCollectingRequired(
            final Supplier<B> builderGetter,
            final C component,
            final BiConsumer<B, C> accumulatingOperation
    ) {
        if (this.isMetadataCollectingRequired()) {
            final B builder = builderGetter.get();
            accumulatingOperation.accept(builder, component);
        }
    }

    private <T> void accumulateComponentToCurrentSearcherBuilder(final T component,
                                                                 final BiConsumer<SearcherBuilder<?>, T> accumulatingOperation) {
        final SearcherBuilder<?> currentBuilder = this.findCurrentSearcherBuilder();
        accumulatingOperation.accept(currentBuilder, component);
    }

    private <T> void accumulateComponentWithRequiredPropertyExtractorToCurrentSearcherBuilder(final T component,
                                                                                              final Function<T, SpecificationPropertyExtractor> getterPropertyExtractor,
                                                                                              final BiConsumer<SearcherBuilder<?>, T> accumulatingOperation) {
        final SpecificationPropertyExtractor requiredPropertyExtractor = getterPropertyExtractor.apply(component);
        this.specificationValidatorBuilder.requiredPropertyExtractor(requiredPropertyExtractor);
        this.accumulateComponentToCurrentSearcherBuilder(component, accumulatingOperation);
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

    private <S extends FuelSearcher, B extends SearcherBuilder<S>> void buildAndAccumulateSearcher(final Supplier<B> builderGetter,
                                                                                                   final Consumer<B> builderSetter) {
        this.buildAndAccumulateSpecificationValidator();
        this.buildAndAccumulateTableMetadata();
        buildAndAccumulateComponent(
                builderGetter,
                this.searchers::add,
                builderSetter
        );
    }

    private void buildAndAccumulateSpecificationValidator() {
        buildAndAccumulateComponent(
                this::getSpecificationValidatorBuilder,
                this.specificationValidators::add,
                this::setSpecificationValidatorBuilder
        );
    }

    private void buildAndAccumulateTableMetadata() {
        if (this.isMetadataCollectingRequired()) {
            buildAndAccumulateComponent(
                    this::getTableMetadataBuilder,
                    this.tablesMetadata::add,
                    this::setTableMetadataBuilder
            );
        }
    }

    private void buildAndAccumulateSubTableTitleMetadata() {
        if (this.isMetadataCollectingRequired()) {
            final SubTableTitleMetadata metadata = buildAndAccumulateComponent(
                    this::getSubTableTitleMetadataBuilder,
                    this.compositeSearcherBuilder::subTableTitleMetadata,
                    this::setSubTableTitleMetadataBuilder
            );
            metadata.getArgumentsMetadata().forEach(this::accumulatePropertyMetadata);
        }
    }

    private static <T, B extends BuilderRequiringAllProperties<T>> T buildAndAccumulateComponent(
            final Supplier<B> componentBuilderGetter,
            final Consumer<T> accumulatingOperation,
            final Consumer<B> builderSetter) {
        final B componentBuilder = componentBuilderGetter.get();
        final T component = componentBuilder.build();
        accumulatingOperation.accept(component);
        builderSetter.accept(null);
        return component;
    }

    private void accumulatePropertyMetadataIfMetadataCollectingRequired(final PropertyMetadataSource source) {
        if (this.isMetadataCollectingRequired()) {
            this.accumulatePropertyMetadata(source);
        }
    }

    private void accumulatePropertyMetadata(final PropertyMetadataSource source) {
        final FuelTable currentTable = this.findCurrentTable();
        final PropertyMetadata propertyMetadata = this.propertyMetadataSearchingManager.find(currentTable, source);
        this.tableMetadataBuilder.propertyMetadata(propertyMetadata);
    }

    private FuelTable findCurrentTable() {
        final SearcherBuilder<?> currentSearcherBuilder = this.findCurrentSearcherBuilder();
        return currentSearcherBuilder.getTable();
    }
}
