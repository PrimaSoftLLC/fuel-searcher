package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler;

import by.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.junit.Test;

import static org.mockito.Mockito.*;

public final class SimpleSearcherTagHandlerTest {
    private final SimpleSearcherTagHandler tagHandler = new SimpleSearcherTagHandler();

    @Test
    public void startTagShouldBeHandled() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);

        this.tagHandler.handleStartTag(givenContext);

        verify(givenContext, times(1)).startParseSimpleSearcher();
    }

    @Test
    public void endTagShouldBeHandled() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);

        this.tagHandler.handleEndTag(givenContext);

        verify(givenContext, times(1)).buildSimpleSearcher();
    }

}
