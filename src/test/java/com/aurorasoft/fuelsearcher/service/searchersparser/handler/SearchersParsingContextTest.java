package com.aurorasoft.fuelsearcher.service.searchersparser.handler;

import com.aurorasoft.fuelsearcher.model.FuelTable;
import com.aurorasoft.fuelsearcher.model.SubTableTitleMetadata;
import com.aurorasoft.fuelsearcher.model.SubTableTitleMetadata.SubTableTitleMetadataBuilder;
import com.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import com.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;
import com.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import com.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher;
import com.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher.CompositeSearcherBuilder;
import com.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import com.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher;
import com.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher.SimpleSearcherBuilder;
import com.aurorasoft.fuelsearcher.testutil.ReflectionUtil;
import lombok.Builder;
import org.junit.Test;
import org.xml.sax.Attributes;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public final class SearchersParsingContextTest {
    private static final String FIELD_NAME_SEARCHERS = "searchers";
    private static final String FIELD_NAME_SIMPLE_SEARCHER_BUILDER = "simpleSearcherBuilder";
    private static final String FIELD_NAME_COMPOSITE_SEARCHER_BUILDER = "compositeSearcherBuilder";
    private static final String FIELD_NAME_SUB_TABLE_TITLE_METADATA_BUILDER = "subTableTitleMetadataBuilder";

    @Test
    public void contextShouldBeCreated() {
        final SearchersParsingContext actual = new SearchersParsingContext();

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::isNull)
                .compositeSearcherBuilderPredicate(Objects::isNull)
                .subTableTitleMetadataBuilderPredicate(Objects::isNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(actual));
    }

    @Test
    public void contextShouldStartParsingSimpleSearcher() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        givenContext.startParsingSimpleSearcher();

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::nonNull)
                .compositeSearcherBuilderPredicate(Objects::isNull)
                .subTableTitleMetadataBuilderPredicate(Objects::isNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));
    }

    @Test
    public void contextShouldStartParsingCompositeSearcher() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        givenContext.startParsingCompositeSearcher();

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::isNull)
                .compositeSearcherBuilderPredicate(Objects::nonNull)
                .subTableTitleMetadataBuilderPredicate(Objects::nonNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));
    }

    @Test
    public void fuelTableShouldBeAccumulatedBySimpleSearcher() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final SimpleSearcherBuilder givenSearcherBuilder = mock(SimpleSearcherBuilder.class);
        setSimpleSearcherBuilder(givenContext, givenSearcherBuilder);

        final FuelTable givenTable = mock(FuelTable.class);
        givenContext.accumulateFuelTable(givenTable);

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::nonNull)
                .compositeSearcherBuilderPredicate(Objects::isNull)
                .subTableTitleMetadataBuilderPredicate(Objects::isNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));

        verify(givenSearcherBuilder, times(1)).table(same(givenTable));
    }

    @Test
    public void fuelTableShouldBeAccumulatedByCompositeSearcher() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final CompositeSearcherBuilder givenSearcherBuilder = mock(CompositeSearcherBuilder.class);
        setCompositeSearcherBuilder(givenContext, givenSearcherBuilder);

        final FuelTable givenTable = mock(FuelTable.class);
        givenContext.accumulateFuelTable(givenTable);

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::isNull)
                .compositeSearcherBuilderPredicate(Objects::nonNull)
                .subTableTitleMetadataBuilderPredicate(Objects::isNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));

        verify(givenSearcherBuilder, times(1)).table(same(givenTable));
    }

    @Test(expected = IllegalStateException.class)
    public void fuelTableShouldNotBeAccumulatedBecauseOfNoNotNullBuilder() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final FuelTable givenTable = mock(FuelTable.class);
        givenContext.accumulateFuelTable(givenTable);
    }

    @Test
    public void fuelTableShouldBeAccumulatedBySimpleSearcherInCaseTwoSearcherBuildersNotNull() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final SimpleSearcherBuilder givenSimpleSearcherBuilder = mock(SimpleSearcherBuilder.class);
        setSimpleSearcherBuilder(givenContext, givenSimpleSearcherBuilder);

        final CompositeSearcherBuilder givenCompositeSearcherBuilder = mock(CompositeSearcherBuilder.class);
        setCompositeSearcherBuilder(givenContext, givenCompositeSearcherBuilder);

        final FuelTable givenTable = mock(FuelTable.class);
        givenContext.accumulateFuelTable(givenTable);

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::nonNull)
                .compositeSearcherBuilderPredicate(Objects::nonNull)
                .subTableTitleMetadataBuilderPredicate(Objects::isNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));

        verify(givenSimpleSearcherBuilder, times(1)).table(same(givenTable));
        verify(givenCompositeSearcherBuilder, times(0)).table(same(givenTable));
    }

    @Test
    public void simpleSearcherShouldBeBuiltAndAccumulated() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final SimpleSearcherBuilder givenBuilder = mock(SimpleSearcherBuilder.class);
        setSimpleSearcherBuilder(givenContext, givenBuilder);

        final SimpleFuelSearcher givenBuiltSearcher = mock(SimpleFuelSearcher.class);
        when(givenBuilder.build()).thenReturn(givenBuiltSearcher);

        givenContext.buildAndAccumulateSimpleSearcher();

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(actualSearchers -> Objects.equals(List.of(givenBuiltSearcher), actualSearchers))
                .simpleSearcherBuilderPredicate(Objects::isNull)
                .compositeSearcherBuilderPredicate(Objects::isNull)
                .subTableTitleMetadataBuilderPredicate(Objects::isNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));
    }

    @Test(expected = NullPointerException.class)
    public void simpleSearcherShouldNotBeBuiltAndAccumulatedBecauseOfSimpleSearcherIsNull() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        givenContext.buildAndAccumulateSimpleSearcher();
    }

    @Test
    public void compositeSearcherShouldBeBuiltAndAccumulated() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final SubTableTitleMetadataBuilder givenSubTableTitleMetadataBuilder = mock(SubTableTitleMetadataBuilder.class);
        setSubTableTitleMetadataBuilder(givenContext, givenSubTableTitleMetadataBuilder);

        final SubTableTitleMetadata givenBuiltSubTableTitleMetadata = mock(SubTableTitleMetadata.class);
        when(givenSubTableTitleMetadataBuilder.build()).thenReturn(givenBuiltSubTableTitleMetadata);

        final CompositeSearcherBuilder givenSearcherBuilder = mock(CompositeSearcherBuilder.class);
        setCompositeSearcherBuilder(givenContext, givenSearcherBuilder);

        final CompositeFuelSearcher givenBuiltSearcher = mock(CompositeFuelSearcher.class);
        when(givenSearcherBuilder.build()).thenReturn(givenBuiltSearcher);

        givenContext.buildAndAccumulateCompositeSearcher();

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(actualSearchers -> Objects.equals(List.of(givenBuiltSearcher), actualSearchers))
                .simpleSearcherBuilderPredicate(Objects::isNull)
                .compositeSearcherBuilderPredicate(Objects::isNull)
                .subTableTitleMetadataBuilderPredicate(Objects::isNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));

        verify(givenSearcherBuilder, times(1)).subTableTitleMetadata(
                same(givenBuiltSubTableTitleMetadata)
        );
    }

    @Test(expected = NullPointerException.class)
    public void compositeSearcherShouldNotBeBuiltAndAccumulatedBecauseOfSubTableTitleMetadataBuilderIsNull() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        givenContext.buildAndAccumulateCompositeSearcher();
    }

    @Test(expected = NullPointerException.class)
    public void compositeSearcherShouldNotBeBuiltAndAccumulatedBecauseOfCompositeSearcherBuilderIsNull() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final SubTableTitleMetadataBuilder givenSubTableTitleMetadataBuilder = mock(SubTableTitleMetadataBuilder.class);
        setSubTableTitleMetadataBuilder(givenContext, givenSubTableTitleMetadataBuilder);

        final SubTableTitleMetadata givenBuiltSubTableTitleMetadata = mock(SubTableTitleMetadata.class);
        when(givenSubTableTitleMetadataBuilder.build()).thenReturn(givenBuiltSubTableTitleMetadata);

        givenContext.buildAndAccumulateCompositeSearcher();
    }

    @Test
    public void headerMetadataShouldBeAccumulatedBySimpleSearcherBuilder() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final SimpleSearcherBuilder givenSearcherBuilder = mock(SimpleSearcherBuilder.class);
        setSimpleSearcherBuilder(givenContext, givenSearcherBuilder);

        final FuelHeaderMetadata givenHeaderMetadata = mock(FuelHeaderMetadata.class);

        givenContext.accumulateFuelHeaderMetadata(givenHeaderMetadata);

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::nonNull)
                .compositeSearcherBuilderPredicate(Objects::isNull)
                .subTableTitleMetadataBuilderPredicate(Objects::isNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));

        verify(givenSearcherBuilder, times(1)).headerMetadata(same(givenHeaderMetadata));
    }

    @Test
    public void headerMetadataShouldBeAccumulatedToCompositeSearcherBuilder() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final CompositeSearcherBuilder givenSearcherBuilder = mock(CompositeSearcherBuilder.class);
        setCompositeSearcherBuilder(givenContext, givenSearcherBuilder);

        final FuelHeaderMetadata givenHeaderMetadata = mock(FuelHeaderMetadata.class);

        givenContext.accumulateFuelHeaderMetadata(givenHeaderMetadata);

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::isNull)
                .compositeSearcherBuilderPredicate(Objects::nonNull)
                .subTableTitleMetadataBuilderPredicate(Objects::isNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));

        verify(givenSearcherBuilder, times(1)).headerMetadata(same(givenHeaderMetadata));
    }

    @Test(expected = IllegalStateException.class)
    public void headerMetadataShouldNotBeAccumulatedBecauseOfSearcherBuildersAreNull() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();
        final FuelHeaderMetadata givenHeaderMetadata = mock(FuelHeaderMetadata.class);

        givenContext.accumulateFuelHeaderMetadata(givenHeaderMetadata);
    }

    @Test
    public void headerMetadataShouldBeAccumulatedBySimpleSearcherBuilderInCaseTwoSearcherBuildersAreNotNull() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final SimpleSearcherBuilder givenSimpleSearcherBuilder = mock(SimpleSearcherBuilder.class);
        setSimpleSearcherBuilder(givenContext, givenSimpleSearcherBuilder);

        final CompositeSearcherBuilder givenCompositeSearcherBuilder = mock(CompositeSearcherBuilder.class);
        setCompositeSearcherBuilder(givenContext, givenCompositeSearcherBuilder);

        final FuelHeaderMetadata givenHeaderMetadata = mock(FuelHeaderMetadata.class);

        givenContext.accumulateFuelHeaderMetadata(givenHeaderMetadata);

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::nonNull)
                .compositeSearcherBuilderPredicate(Objects::nonNull)
                .subTableTitleMetadataBuilderPredicate(Objects::isNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));

        verify(givenSimpleSearcherBuilder, times(1)).headerMetadata(same(givenHeaderMetadata));
        verify(givenCompositeSearcherBuilder, times(0)).headerMetadata(same(givenHeaderMetadata));
    }

    @Test
    public void interimFilterShouldBeAccumulatedBySimpleSearcherBuilder() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final SimpleSearcherBuilder givenSearcherBuilder = mock(SimpleSearcherBuilder.class);
        setSimpleSearcherBuilder(givenContext, givenSearcherBuilder);

        final InterimFilter givenFilter = mock(InterimFilter.class);

        givenContext.accumulateFilter(givenFilter);

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::nonNull)
                .compositeSearcherBuilderPredicate(Objects::isNull)
                .subTableTitleMetadataBuilderPredicate(Objects::isNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));

        verify(givenSearcherBuilder, times(1)).interimFilter(same(givenFilter));
    }

    @Test
    public void interimFilterShouldBeAccumulatedByCompositeSearcherBuilder() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final CompositeSearcherBuilder givenSearcherBuilder = mock(CompositeSearcherBuilder.class);
        setCompositeSearcherBuilder(givenContext, givenSearcherBuilder);

        final InterimFilter givenFilter = mock(InterimFilter.class);

        givenContext.accumulateFilter(givenFilter);

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::isNull)
                .compositeSearcherBuilderPredicate(Objects::nonNull)
                .subTableTitleMetadataBuilderPredicate(Objects::isNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));

        verify(givenSearcherBuilder, times(1)).interimFilter(same(givenFilter));
    }

    @Test(expected = IllegalStateException.class)
    public void interimFilterShouldNotBeAccumulatedBecauseOfSearcherBuildersAreNull() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();
        final InterimFilter givenFilter = mock(InterimFilter.class);

        givenContext.accumulateFilter(givenFilter);
    }

    @Test
    public void interimFilterShouldBeAccumulatedBySimpleSearcherBuilderInCaseTwoSearcherBuildersAreNotNull() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final SimpleSearcherBuilder givenSimpleSearcherBuilder = mock(SimpleSearcherBuilder.class);
        setSimpleSearcherBuilder(givenContext, givenSimpleSearcherBuilder);

        final CompositeSearcherBuilder givenCompositeSearcherBuilder = mock(CompositeSearcherBuilder.class);
        setCompositeSearcherBuilder(givenContext, givenCompositeSearcherBuilder);

        final InterimFilter givenFilter = mock(InterimFilter.class);

        givenContext.accumulateFilter(givenFilter);

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::nonNull)
                .compositeSearcherBuilderPredicate(Objects::nonNull)
                .subTableTitleMetadataBuilderPredicate(Objects::isNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));

        verify(givenSimpleSearcherBuilder, times(1)).interimFilter(same(givenFilter));
        verify(givenCompositeSearcherBuilder, times(0)).interimFilter(same(givenFilter));
    }

    @Test
    public void finalFilterShouldBeAccumulatedBySimpleSearcherBuilder() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final SimpleSearcherBuilder givenSearcherBuilder = mock(SimpleSearcherBuilder.class);
        setSimpleSearcherBuilder(givenContext, givenSearcherBuilder);

        final FinalFilter givenFilter = mock(FinalFilter.class);

        givenContext.accumulateFilter(givenFilter);

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::nonNull)
                .compositeSearcherBuilderPredicate(Objects::isNull)
                .subTableTitleMetadataBuilderPredicate(Objects::isNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));

        verify(givenSearcherBuilder, times(1)).finalFilter(same(givenFilter));
    }

    @Test
    public void finalFilterShouldBeAccumulatedByCompositeSearcherBuilder() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final CompositeSearcherBuilder givenSearcherBuilder = mock(CompositeSearcherBuilder.class);
        setCompositeSearcherBuilder(givenContext, givenSearcherBuilder);

        final FinalFilter givenFilter = mock(FinalFilter.class);

        givenContext.accumulateFilter(givenFilter);

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::isNull)
                .compositeSearcherBuilderPredicate(Objects::nonNull)
                .subTableTitleMetadataBuilderPredicate(Objects::isNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));

        verify(givenSearcherBuilder, times(1)).finalFilter(same(givenFilter));
    }

    @Test(expected = IllegalStateException.class)
    public void finalFilterShouldNotBeAccumulatedBecauseOfSearcherBuildersAreNull() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();
        final FinalFilter givenFilter = mock(FinalFilter.class);

        givenContext.accumulateFilter(givenFilter);
    }

    @Test
    public void finalFilterShouldBeAccumulatedBySimpleSearcherBuilderInCaseTwoSearcherBuildersAreNotNull() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final SimpleSearcherBuilder givenSimpleSearcherBuilder = mock(SimpleSearcherBuilder.class);
        setSimpleSearcherBuilder(givenContext, givenSimpleSearcherBuilder);

        final CompositeSearcherBuilder givenCompositeSearcherBuilder = mock(CompositeSearcherBuilder.class);
        setCompositeSearcherBuilder(givenContext, givenCompositeSearcherBuilder);

        final FinalFilter givenFilter = mock(FinalFilter.class);

        givenContext.accumulateFilter(givenFilter);

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::nonNull)
                .compositeSearcherBuilderPredicate(Objects::nonNull)
                .subTableTitleMetadataBuilderPredicate(Objects::isNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));

        verify(givenSimpleSearcherBuilder, times(1)).finalFilter(same(givenFilter));
        verify(givenCompositeSearcherBuilder, times(0)).finalFilter(same(givenFilter));
    }

    @Test
    public void subTableTitleTemplateShouldBeAccumulated() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final SubTableTitleMetadataBuilder givenSubTableTitleMetadataBuilder = mock(SubTableTitleMetadataBuilder.class);
        setSubTableTitleMetadataBuilder(givenContext, givenSubTableTitleMetadataBuilder);

        final String givenTemplate = "{first-property} and {second-property}";

        givenContext.accumulateSubTableTitleTemplate(givenTemplate);

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::isNull)
                .compositeSearcherBuilderPredicate(Objects::isNull)
                .subTableTitleMetadataBuilderPredicate(Objects::nonNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));

        verify(givenSubTableTitleMetadataBuilder, times(1)).templateWithPropertyNames(
                same(givenTemplate)
        );
    }

    @Test(expected = NullPointerException.class)
    public void subTableTitleTemplateShouldNotBeAccumulatedBecauseOfSubTableTitleMetadataBuilderIsNull() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();
        final String givenTemplate = "{first-property} and {second-property}";

        givenContext.accumulateSubTableTitleTemplate(givenTemplate);
    }

    @Test
    public void subTableTitleTemplateArgumentExtractorShouldBeAccumulated() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final SubTableTitleMetadataBuilder givenSubTableTitleMetadataBuilder = mock(SubTableTitleMetadataBuilder.class);
        setSubTableTitleMetadataBuilder(givenContext, givenSubTableTitleMetadataBuilder);

        final SpecificationPropertyExtractor givenExtractor = mock(SpecificationPropertyExtractor.class);

        givenContext.accumulateSubTableTitleTemplateArgumentExtractor(givenExtractor);

        final ContextStateMatcher contextStateMatcher = ContextStateMatcher.builder()
                .searchersPredicate(Collection::isEmpty)
                .simpleSearcherBuilderPredicate(Objects::isNull)
                .compositeSearcherBuilderPredicate(Objects::isNull)
                .subTableTitleMetadataBuilderPredicate(Objects::nonNull)
                .lastContentPredicate(Objects::isNull)
                .lastAttributesPredicate(Objects::isNull)
                .build();
        assertTrue(contextStateMatcher.isMatch(givenContext));

        verify(givenSubTableTitleMetadataBuilder, times(1)).argumentExtractor(
                same(givenExtractor)
        );
    }

    @Test(expected = NullPointerException.class)
    public void subTableTitleTemplateArgumentExtractorShouldNotBeAccumulatedBecauseOfSubTableTitleMetadataBuilderIsNull() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();
        final SpecificationPropertyExtractor givenExtractor = mock(SpecificationPropertyExtractor.class);

        givenContext.accumulateSubTableTitleTemplateArgumentExtractor(givenExtractor);
    }

    @Test
    public void parsedSearchersShouldBeFound() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final List<FuelSearcher> givenSearchers = List.of(
                mock(FuelSearcher.class),
                mock(FuelSearcher.class),
                mock(FuelSearcher.class)
        );
        setSearchers(givenContext, givenSearchers);

        final List<FuelSearcher> actual = givenContext.findParsedSearchers();
        assertSame(givenSearchers, actual);
    }

    private static final class ContextStateMatcher {
        private final Predicate<SearchersParsingContext> searchersPredicate;
        private final Predicate<SearchersParsingContext> simpleSearcherBuilderPredicate;
        private final Predicate<SearchersParsingContext> compositeSearcherBuilderPredicate;
        private final Predicate<SearchersParsingContext> subTableTitleMetadataBuilderPredicate;
        private final Predicate<SearchersParsingContext> lastContentPredicate;
        private final Predicate<SearchersParsingContext> lastAttributesPredicate;

        @Builder
        public ContextStateMatcher(final Predicate<List<FuelSearcher>> searchersPredicate,
                                   final Predicate<SimpleSearcherBuilder> simpleSearcherBuilderPredicate,
                                   final Predicate<CompositeSearcherBuilder> compositeSearcherBuilderPredicate,
                                   final Predicate<SubTableTitleMetadataBuilder> subTableTitleMetadataBuilderPredicate,
                                   final Predicate<String> lastContentPredicate,
                                   final Predicate<Attributes> lastAttributesPredicate) {
            this.searchersPredicate = mapToContextPredicate(
                    searchersPredicate,
                    SearchersParsingContextTest::findSearchers
            );
            this.simpleSearcherBuilderPredicate = mapToContextPredicate(
                    simpleSearcherBuilderPredicate,
                    SearchersParsingContextTest::findSimpleSearcherBuilder
            );
            this.compositeSearcherBuilderPredicate = mapToContextPredicate(
                    compositeSearcherBuilderPredicate,
                    SearchersParsingContextTest::findCompositeSearcherBuilder
            );
            this.subTableTitleMetadataBuilderPredicate = mapToContextPredicate(
                    subTableTitleMetadataBuilderPredicate,
                    SearchersParsingContextTest::findSubTableTitleMetadataBuilder
            );
            this.lastContentPredicate = mapToContextPredicate(
                    lastContentPredicate,
                    SearchersParsingContext::getLastContent
            );
            this.lastAttributesPredicate = mapToContextPredicate(
                    lastAttributesPredicate,
                    SearchersParsingContext::getLastAttributes
            );
        }

        public boolean isMatch(final SearchersParsingContext context) {
            final Predicate<SearchersParsingContext> contextStatePredicate = this.createContextStatePredicate();
            return contextStatePredicate.test(context);
        }

        private static <P> Predicate<SearchersParsingContext> mapToContextPredicate(
                final Predicate<P> propertyPredicate,
                final Function<SearchersParsingContext, P> propertyExtractor
        ) {
            return context -> propertyPredicate.test(propertyExtractor.apply(context));
        }

        private Predicate<SearchersParsingContext> createContextStatePredicate() {
            return Stream.of(
                    this.searchersPredicate,
                    this.simpleSearcherBuilderPredicate,
                    this.compositeSearcherBuilderPredicate,
                    this.subTableTitleMetadataBuilderPredicate,
                    this.lastContentPredicate,
                    this.lastAttributesPredicate
            ).reduce(Predicate::and).orElseThrow(IllegalStateException::new);
        }
    }

    @SuppressWarnings("unchecked")
    private static List<FuelSearcher> findSearchers(final SearchersParsingContext context) {
        return ReflectionUtil.findProperty(
                context,
                FIELD_NAME_SEARCHERS,
                List.class
        );
    }

    private static SimpleSearcherBuilder findSimpleSearcherBuilder(final SearchersParsingContext context) {
        return ReflectionUtil.findProperty(
                context,
                FIELD_NAME_SIMPLE_SEARCHER_BUILDER,
                SimpleSearcherBuilder.class
        );
    }

    private static CompositeSearcherBuilder findCompositeSearcherBuilder(final SearchersParsingContext context) {
        return ReflectionUtil.findProperty(
                context,
                FIELD_NAME_COMPOSITE_SEARCHER_BUILDER,
                CompositeSearcherBuilder.class
        );
    }

    private static SubTableTitleMetadataBuilder findSubTableTitleMetadataBuilder(final SearchersParsingContext context) {
        return ReflectionUtil.findProperty(
                context,
                FIELD_NAME_SUB_TABLE_TITLE_METADATA_BUILDER,
                SubTableTitleMetadataBuilder.class
        );
    }

    private static void setSimpleSearcherBuilder(final SearchersParsingContext context,
                                                 final SimpleSearcherBuilder builder) {
        ReflectionUtil.setProperty(
                context,
                builder,
                FIELD_NAME_SIMPLE_SEARCHER_BUILDER
        );
    }

    private static void setCompositeSearcherBuilder(final SearchersParsingContext context,
                                                    final CompositeSearcherBuilder builder) {
        ReflectionUtil.setProperty(
                context,
                builder,
                FIELD_NAME_COMPOSITE_SEARCHER_BUILDER
        );
    }

    private static void setSubTableTitleMetadataBuilder(final SearchersParsingContext context,
                                                        final SubTableTitleMetadataBuilder builder) {
        ReflectionUtil.setProperty(
                context,
                builder,
                FIELD_NAME_SUB_TABLE_TITLE_METADATA_BUILDER
        );
    }

    private static void setSearchers(final SearchersParsingContext context, final List<FuelSearcher> searchers) {
        ReflectionUtil.setProperty(
                context,
                searchers,
                FIELD_NAME_SEARCHERS
        );
    }
}
