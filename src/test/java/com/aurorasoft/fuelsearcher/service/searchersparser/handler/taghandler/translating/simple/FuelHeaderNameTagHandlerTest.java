package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple;

import com.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.junit.Test;

import static org.mockito.Mockito.*;

public final class FuelHeaderNameTagHandlerTest {
    private final FuelHeaderNameTagHandler tagHandler = new FuelHeaderNameTagHandler(null);

    @Test
    public void startTagShouldBeHandled() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);

        this.tagHandler.handleStartTag(givenContext);
    }

    @Test
    public void translatedValueShouldBeAccumulated() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);
        final FuelHeaderMetadata givenMetadata = mock(FuelHeaderMetadata.class);

        this.tagHandler.accumulateTranslatedValue(givenContext, givenMetadata);

        verify(givenContext, times(1)).accumulateFuelHeaderMetadata(same(givenMetadata));
    }

    @Test
    public void additionalValuesShouldBeAccumulated() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);

        this.tagHandler.accumulateAdditionalValues(givenContext);
    }
}
