package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler;

import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.junit.Test;

import static org.mockito.Mockito.*;

public final class CompositeSearcherTagHandlerTest {
    private final CompositeSearcherTagHandler tagHandler = new CompositeSearcherTagHandler();

    @Test
    public void startTagShouldBeHandled() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);

        this.tagHandler.handleStartTag(givenContext);

        verify(givenContext, times(1)).startParsingCompositeSearcher();
    }

    @Test
    public void endTagShouldBeHandled() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);

        this.tagHandler.handleEndTag(givenContext);

        verify(givenContext, times(1)).buildAndAccumulateCompositeSearcher();
    }
}
