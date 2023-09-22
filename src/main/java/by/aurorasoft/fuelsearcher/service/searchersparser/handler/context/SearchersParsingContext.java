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

    private SpecificationValidatorBuilder specificationValidatorBuilder;

    private TableMetadataBuilder tableMetadataBuilder;

    @Setter
    @Getter
    private String lastContent;

    @Setter
    @Getter
    private Attributes lastAttributes;

    public SearchersParsingContext(final PropertyMetadataSearchingManager propertyMetadataSearchingManager) {
        this.propertyMetadataSearchingManager = propertyMetadataSearchingManager;
        this.searchers = new ArrayList<>();
        this.specificationValidators = new ArrayList<>();
        this.tablesMetadata = new ArrayList<>();
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
        this.subTableTitleMetadataBuilder = SubTableTitleMetadata.builder();
    }

    public void accumulateFuelTable(final FuelTable fuelTable) {
        final String tableName = fuelTable.name();
        this.specificationValidatorBuilder.tableName(tableName);
        this.tableMetadataBuilder.tableName(tableName);
        this.accumulateComponentToCurrentSearcherBuilder(
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
        this.compositeSearcherBuilder.subTableTitleMetadata(this.subTableTitleMetadataBuilder.build());
        this.subTableTitleMetadataBuilder = null;
        this.buildSearcher(
                SearchersParsingContext::getCompositeSearcherBuilder,
                SearchersParsingContext::setCompositeSearcherBuilder
        );
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
        this.subTableTitleMetadataBuilder.template(template);
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

    private <B extends SearcherBuilder<?>> void buildSearcher(final Function<SearchersParsingContext, B> builderGetter,
                                                              final BiConsumer<SearchersParsingContext, B> builderSetter) {
        this.buildSpecificationValidator();
        this.buildTableMetadata();
        final B builder = builderGetter.apply(this);
        final FuelSearcher searcher = builder.build();
        this.searchers.add(searcher);
        builderSetter.accept(this, null);
    }

    private void buildSpecificationValidator() {
        final SpecificationValidator validator = this.specificationValidatorBuilder.build();
        this.specificationValidators.add(validator);
        this.specificationValidatorBuilder = null;
    }

    private void buildTableMetadata() {
        final TableMetadata metadata = this.tableMetadataBuilder.build();
        this.tablesMetadata.add(metadata);
        this.tableMetadataBuilder = null;
    }
}