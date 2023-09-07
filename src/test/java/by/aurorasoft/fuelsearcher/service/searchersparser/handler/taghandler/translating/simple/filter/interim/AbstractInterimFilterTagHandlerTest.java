package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple.filter.interim;

import by.aurorasoft.fuelsearcher.model.filter.factory.interim.InterimFilterFactory;
import by.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.junit.Test;

import java.util.OptionalInt;

import static org.mockito.Mockito.*;

public final class AbstractInterimFilterTagHandlerTest {
    private final TestInterimFilterTagHandler tagHandler = new TestInterimFilterTagHandler();

    @Test
    public void filterShouldBeAccumulated() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);
        final InterimFilter givenFilter = mock(InterimFilter.class);

        this.tagHandler.accumulateFilter(givenContext, givenFilter);

        verify(givenContext, times(1)).accumulateFilter(same(givenFilter));
    }

    private static final class TestInterimFilterTagHandler
            extends AbstractInterimFilterTagHandler<InterimFilter, InterimFilterFactory<?, ?>> {

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
