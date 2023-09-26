package by.aurorasoft.fuelsearcher.service.searchersparser.handler.context;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata.TableMetadataBuilder;
import by.aurorasoft.fuelsearcher.model.FuelTable;
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

//TODO: refactor and refactor tests
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

    private SubTableTitleMetadataBuilder subTableTitleMetadataBuilder;

    @Setter(value = PRIVATE)
    private SpecificationValidatorBuilder specificationValidatorBuilder;

    @Setter(value = PRIVATE)
    private TableMetadataBuilder tableMetadataBuilder;

    @Setter
    @Getter
    private String lastContent;

    @Setter
    @Getter
    private Attributes lastAttributes;

    //TODO: already refactored
    public SearchersParsingContext(final PropertyMetadataSearchingManager propertyMetadataSearchingManager) {
        this.propertyMetadataSearchingManager = propertyMetadataSearchingManager;
        this.searchers = new ArrayList<>();
        this.specificationValidators = new ArrayList<>();
        this.tablesMetadata = new ArrayList<>();
    }

    //TODO: already refactored
    public void startParseSimpleSearcher() {
        this.startParseSearcher(SimpleFuelSearcher::builder, this::setSimpleSearcherBuilder);
    }

    //TODO: already refactored
    public void startParseCompositeSearcher() {
        this.startParseSearcher(CompositeFuelSearcher::builder, this::setCompositeSearcherBuilder);
        this.subTableTitleMetadataBuilder = SubTableTitleMetadata.builder();
    }

    //TODO: already refactored
    public void accumulateFuelTable(final FuelTable fuelTable) {
        final String tableName = fuelTable.name();
        this.specificationValidatorBuilder.tableName(tableName);
        this.tableMetadataBuilder.tableName(tableName);
        this.accumulateComponentToCurrentSearcherBuilder(fuelTable, SearcherBuilder::table);
    }

    public void buildSimpleSearcher() {
//        this.buildSearcher(
//                SearchersParsingContext::getSimpleSearcherBuilder,
//                SearchersParsingContext::setSimpleSearcherBuilder
//        );
    }

    public void buildCompositeSearcher() {
        final SubTableTitleMetadata metadata = this.subTableTitleMetadataBuilder.build();
        metadata.getArgumentsMetadata().forEach(argumentMetadata -> this.tableMetadataBuilder.propertyMetadata(
                this.propertyMetadataSearchingManager.find(this.findCurrentBuilder().getTable(), argumentMetadata)));

        this.compositeSearcherBuilder.subTableTitleMetadata(this.subTableTitleMetadataBuilder.build());
        this.subTableTitleMetadataBuilder = null;
//        this.buildSearcher(
//                SearchersParsingContext::getCompositeSearcherBuilder,
//                SearchersParsingContext::setCompositeSearcherBuilder
//        );
    }

    public void accumulateFuelHeaderMetadata(final FuelHeaderMetadata metadata) {
        this.accumulateComponentWithRequiredPropertyExtractorToCurrentSearcherBuilder(
                metadata,
                FuelHeaderMetadata::getHeaderExtractor,
                SearcherBuilder::headerMetadata
        );
        final PropertyMetadata propertyMetadata = this.propertyMetadataSearchingManager.find(this.findCurrentBuilder().getTable(), metadata);
        this.tableMetadataBuilder.propertyMetadata(propertyMetadata);
    }

    public void accumulateFilter(final InterimFilter filter) {
        this.accumulateComponentWithRequiredPropertyExtractorToCurrentSearcherBuilder(
                filter,
                InterimFilter::getFiltrationValueExtractor,
                SearcherBuilder::interimFilter
        );
        final PropertyMetadata propertyMetadata = this.propertyMetadataSearchingManager.find(this.findCurrentBuilder().getTable(), filter);
        this.tableMetadataBuilder.propertyMetadata(propertyMetadata);
    }

    public void accumulateFilter(final FinalFilter filter) {
        this.accumulateComponentWithRequiredPropertyExtractorToCurrentSearcherBuilder(
                filter,
                FinalFilter::getFiltrationValueExtractor,
                SearcherBuilder::finalFilter
        );
        final PropertyMetadata propertyMetadata = this.propertyMetadataSearchingManager.find(this.findCurrentBuilder().getTable(), filter);
        this.tableMetadataBuilder.propertyMetadata(propertyMetadata);
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

    //TODO: already refactored
    private <B extends SearcherBuilder<?>> void startParseSearcher(final Supplier<B> builderSupplier,
                                                                   final Consumer<B> builderSetter) {
        final B searcherBuilder = builderSupplier.get();
        builderSetter.accept(searcherBuilder);
        this.specificationValidatorBuilder = SpecificationValidator.builder();
        this.tableMetadataBuilder = TableMetadata.builder();
    }

    private <T> void accumulateComponentToCurrentSearcherBuilder(final T component,
                                                                 final BiConsumer<SearcherBuilder<?>, T> accumulatingOperation) {
        final SearcherBuilder<?> currentBuilder = this.findCurrentBuilder();
        accumulatingOperation.accept(currentBuilder, component);
    }

    private <T> void accumulateComponentWithRequiredPropertyExtractorToCurrentSearcherBuilder(final T component,
                                                                                              final Function<T, SpecificationPropertyExtractor> getterPropertyExtractor,
                                                                                              final BiConsumer<SearcherBuilder<?>, T> accumulatingOperation) {
        final SpecificationPropertyExtractor requiredPropertyExtractor = getterPropertyExtractor.apply(component);
        this.specificationValidatorBuilder.requiredPropertyExtractor(requiredPropertyExtractor);
        this.accumulateComponentToCurrentSearcherBuilder(component, accumulatingOperation);
    }

    private SearcherBuilder<?> findCurrentBuilder() {
        if (this.simpleSearcherBuilder != null) {
            return this.simpleSearcherBuilder;
        } else {
            return this.compositeSearcherBuilder;
        }
    }

    private <B extends SearcherBuilder<?>> void buildSearcher(final Supplier<B> builderGetter,
                                                              final Consumer<B> builderSetter) {
//        this.buildSpecificationValidator();
//        this.buildTableMetadata();
//        final B builder = builderGetter.apply(this);
//
//        buildAndAccumulateResultComponent(
//                builder,
//                this.searchers::add,
//                builderSetter
//        );

        final B builder = null;

        //TODO: do method for this and next two methods: buildSpecificationValidator and buildTableMetadata
        final FuelSearcher searcher = builder.build();
        this.searchers.add(searcher);
//        builderSetter.accept(this, null);
    }

    //TODO: refactored
    private void buildSpecificationValidator() {
        buildAndAccumulateResultComponent(
                this.specificationValidatorBuilder,
                this.specificationValidators::add,
                this::setSpecificationValidatorBuilder
        );
    }

    //TODO: refactored
    private void buildTableMetadata() {
        buildAndAccumulateResultComponent(
                this.tableMetadataBuilder,
                this.tablesMetadata::add,
                this::setTableMetadataBuilder
        );
    }

    //TODO: refactored
    private static <T, B extends BuilderRequiringAllProperties<T>> void buildAndAccumulateResultComponent(
            final BuilderRequiringAllProperties<T> componentBuilder,
            final Consumer<T> accumulatingOperation,
            final Consumer<B> builderSetter) {
        final T component = componentBuilder.build();
        accumulatingOperation.accept(component);
        builderSetter.accept(null);
    }
}
