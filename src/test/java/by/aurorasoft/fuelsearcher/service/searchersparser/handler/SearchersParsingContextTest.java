package by.aurorasoft.fuelsearcher.service.searchersparser.handler;

import by.aurorasoft.fuelsearcher.model.SubTableTitleMetadata.SubTableTitleMetadataBuilder;
import by.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher.CompositeSearcherBuilder;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
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
import static org.junit.Assert.assertTrue;

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
}
