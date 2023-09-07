package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating;

import by.aurorasoft.fuelsearcher.service.dictionary.Dictionary;
import by.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.exception.NoSuchKeyException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Optional.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class TranslatingTagHandlerTest {

    @Test
    @SuppressWarnings("unchecked")
    public void endTagShouldBeHandled() {
        final String givenTagName = "tag-name";
        final Dictionary<TestValue> givenDictionary = mock(Dictionary.class);

        final String firstGivenAlias = "first-alias";
        final String secondGivenAlias = "second-alias";
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);
        final List<String> givenAliases = List.of(firstGivenAlias, secondGivenAlias);

        final TestTranslatingTagHandler givenTagHandler = new TestTranslatingTagHandler(
                givenTagName, givenDictionary, givenContext, givenAliases
        );

        final TestValue firstGivenValue = new TestValue(firstGivenAlias);
        when(givenDictionary.find(same(firstGivenAlias))).thenReturn(Optional.of(firstGivenValue));

        final TestValue secondGivenValue = new TestValue(secondGivenAlias);
        when(givenDictionary.find(same(secondGivenAlias))).thenReturn(Optional.of(secondGivenValue));

        givenTagHandler.handleEndTag(givenContext);

        final List<TestValue> actualAccumulatedTranslatedValues = givenTagHandler.getAccumulatedTranslatedValues();
        final List<TestValue> expectedAccumulatedTranslatedValues = List.of(firstGivenValue, secondGivenValue);
        assertEquals(expectedAccumulatedTranslatedValues, actualAccumulatedTranslatedValues);

        assertTrue(givenTagHandler.isAdditionalValuesAccumulated());
    }

    @Test(expected = TestTranslatingTagHandler.NoSuchValueException.class)
    @SuppressWarnings("unchecked")
    public void endTagShouldNotBeHandledBecauseOfNoSuchDictionaryKeyException() {
        final String givenTagName = "tag-name";
        final Dictionary<TestValue> givenDictionary = mock(Dictionary.class);

        final String firstGivenAlias = "first-alias";
        final String secondGivenAlias = "second-alias";
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);
        final List<String> givenAliases = List.of(firstGivenAlias, secondGivenAlias);

        final TestTranslatingTagHandler givenTagHandler = new TestTranslatingTagHandler(
                givenTagName, givenDictionary, givenContext, givenAliases
        );

        final TestValue firstGivenValue = new TestValue(firstGivenAlias);
        when(givenDictionary.find(same(firstGivenAlias))).thenReturn(Optional.of(firstGivenValue));

        when(givenDictionary.find(same(secondGivenAlias))).thenReturn(empty());

        givenTagHandler.handleEndTag(givenContext);
    }

    @RequiredArgsConstructor
    private static final class TestValue implements Translatable {
        private final String alias;

        @Override
        public String findAlias() {
            return this.alias;
        }
    }

    private static final class TestTranslatingTagHandler extends TranslatingTagHandler<TestValue> {
        private final List<String> aliases;

        @Getter
        private final List<TestValue> accumulatedTranslatedValues;

        private final SearchersParsingContext expectedContext;

        @Getter
        private boolean additionalValuesAccumulated;

        public TestTranslatingTagHandler(final String tagName,
                                         final Dictionary<TestValue> dictionary,
                                         final SearchersParsingContext expectedContext,
                                         final List<String> aliases) {
            super(tagName, dictionary, NoSuchValueException::new);
            this.aliases = aliases;
            this.accumulatedTranslatedValues = new ArrayList<>();
            this.expectedContext = expectedContext;
            this.additionalValuesAccumulated = false;
        }

        @Override
        public void handleStartTag(final SearchersParsingContext context) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected Stream<String> findAliases(final SearchersParsingContext context) {
            this.checkContext(context);
            return this.aliases.stream();
        }

        @Override
        protected void accumulateTranslatedValue(final SearchersParsingContext context, final TestValue value) {
            this.checkContext(context);
            this.accumulatedTranslatedValues.add(value);
        }

        @Override
        protected void accumulateAdditionalValues(final SearchersParsingContext context) {
            this.checkContext(context);
            this.additionalValuesAccumulated = true;
        }

        private void checkContext(final SearchersParsingContext actual) {
            if (actual != this.expectedContext) {
                throw new IllegalArgumentException("Contexts are not identical");
            }
        }

        private static final class NoSuchValueException extends NoSuchKeyException {

            @SuppressWarnings("unused")
            public NoSuchValueException() {

            }

            public NoSuchValueException(final String key) {
                super(key);
            }

            @SuppressWarnings("unused")
            public NoSuchValueException(final Exception cause) {
                super(cause);
            }

            @SuppressWarnings("unused")
            public NoSuchValueException(final String key, final Exception cause) {
                super(key, cause);
            }
        }
    }
}
