package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple.filter.interim;

import com.aurorasoft.fuelsearcher.service.factory.filter.interim.InterimFilterFactory;
import com.aurorasoft.fuelsearcher.service.filter.interim.InterimFilter;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.junit.Test;

import java.util.OptionalInt;

import static org.mockito.Mockito.*;

public final class InterimFilterTagHandlerTest {
    private final TestInterimFilterTagHandler tagHandler = new TestInterimFilterTagHandler();

    @Test
    public void filterShouldBeAccumulated() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);
        final InterimFilter givenFilter = mock(InterimFilter.class);

        this.tagHandler.accumulateFilter(givenContext, givenFilter);

        verify(givenContext, times(1)).accumulateFilter(same(givenFilter));
    }

    private static final class TestInterimFilterTagHandler
            extends InterimFilterTagHandler<InterimFilter, InterimFilterFactory<?, ?>> {

        public TestInterimFilterTagHandler() {
            super(null, null);
        }

        @Override
        public void handleStartTag(final SearchersParsingContext context) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected void accumulateAdditionalValues(final SearchersParsingContext context) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected OptionalInt findDefaultFiltrationCellIndex() {
            throw new UnsupportedOperationException();
        }
    }
}
