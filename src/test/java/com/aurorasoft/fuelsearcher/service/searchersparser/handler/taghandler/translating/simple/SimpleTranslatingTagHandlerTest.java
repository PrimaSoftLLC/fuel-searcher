package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple;

import com.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class SimpleTranslatingTagHandlerTest {

    @Test
    public void aliasesShouldBeFound() {
        final String givenLastContent = "key";
        final SearchersParsingContext givenContext = createContext(givenLastContent);
        final TestSimpleTranslatingTagHandler givenTagHandler = new TestSimpleTranslatingTagHandler();

        final Stream<String> actual = givenTagHandler.findAliases(givenContext);
        final List<String> actualAsList = actual.toList();
        final List<String> expectedAsList = List.of(givenLastContent);
        assertEquals(expectedAsList, actualAsList);
    }

    @SuppressWarnings("SameParameterValue")
    private static SearchersParsingContext createContext(final String lastContent) {
        final SearchersParsingContext context = mock(SearchersParsingContext.class);
        when(context.getLastContent()).thenReturn(lastContent);
        return context;
    }

    private record TestValue(String alias) implements Translatable {
        @Override
        public String findAlias() {
            return this.alias;
        }
    }

    private static final class TestSimpleTranslatingTagHandler extends SimpleTranslatingTagHandler<TestValue> {

        public TestSimpleTranslatingTagHandler() {
            super(null, null, null);
        }

        @Override
        public void handleStartTag(final SearchersParsingContext context) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected void accumulateTranslatedValue(final SearchersParsingContext context, final TestValue value) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected void accumulateAdditionalValues(final SearchersParsingContext context) {
            throw new UnsupportedOperationException();
        }
    }
}
