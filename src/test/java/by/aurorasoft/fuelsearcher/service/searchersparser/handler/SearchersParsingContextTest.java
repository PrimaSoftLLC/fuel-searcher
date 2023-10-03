package by.aurorasoft.fuelsearcher.service.searchersparser.handler;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.SubTableTitleMetadata.SubTableTitleMetadataBuilder;
import by.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher.CompositeSearcherBuilder;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher.SimpleSearcherBuilder;
import lombok.Builder;
import org.junit.Test;
import org.xml.sax.Attributes;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findProperty;
import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.setProperty;
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
    public void fuelTableShouldNotBeAccumulatedBecauseOfNoInitializedBuilder() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        final FuelTable givenTable = mock(FuelTable.class);
        givenContext.accumulateFuelTable(givenTable);
    }

    @Test
    public void fuelTableShouldBeAccumulatedBySimpleSearcherInCaseInitializedTwoSearcherBuilders() {
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
    public void simpleSearcherShouldNotBeBuiltAndAccumulatedBecauseOfSimpleSearcherIsNotInitialized() {
        final SearchersParsingContext givenContext = new SearchersParsingContext();

        givenContext.buildAndAccumulateSimpleSearcher();
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
        return findProperty(
                context,
                FIELD_NAME_SEARCHERS,
                List.class
        );
    }

    private static SimpleSearcherBuilder findSimpleSearcherBuilder(final SearchersParsingContext context) {
        return findProperty(
                context,
                FIELD_NAME_SIMPLE_SEARCHER_BUILDER,
                SimpleSearcherBuilder.class
        );
    }

    private static CompositeSearcherBuilder findCompositeSearcherBuilder(final SearchersParsingContext context) {
        return findProperty(
                context,
                FIELD_NAME_COMPOSITE_SEARCHER_BUILDER,
                CompositeSearcherBuilder.class
        );
    }

    private static SubTableTitleMetadataBuilder findSubTableTitleMetadataBuilder(final SearchersParsingContext context) {
        return findProperty(
                context,
                FIELD_NAME_SUB_TABLE_TITLE_METADATA_BUILDER,
                SubTableTitleMetadataBuilder.class
        );
    }

    private static void setSimpleSearcherBuilder(final SearchersParsingContext context,
                                                 final SimpleSearcherBuilder builder) {
        setProperty(
                context,
                builder,
                FIELD_NAME_SIMPLE_SEARCHER_BUILDER
        );
    }

    private static void setCompositeSearcherBuilder(final SearchersParsingContext context,
                                                    final CompositeSearcherBuilder builder) {
        setProperty(
                context,
                builder,
                FIELD_NAME_COMPOSITE_SEARCHER_BUILDER
        );
    }
}
