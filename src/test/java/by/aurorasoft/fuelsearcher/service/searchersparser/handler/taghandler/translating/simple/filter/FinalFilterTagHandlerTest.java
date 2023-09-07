package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple.filter;

import by.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.junit.Test;

import java.util.OptionalInt;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public final class FinalFilterTagHandlerTest {
    private final FinalFilterTagHandler tagHandler = new FinalFilterTagHandler(null);

    @Test
    public void startTagShouldBeHandled() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);

        this.tagHandler.handleStartTag(givenContext);
    }

    @Test
    public void defaultFiltrationCellIndexShouldBeFound() {
        final OptionalInt optionalActual = this.tagHandler.findDefaultFiltrationCellIndex();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void filterShouldBeAccumulated() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);
        final FinalFilter givenFilter = mock(FinalFilter.class);

        this.tagHandler.accumulateFilter(givenContext, givenFilter);

        verify(givenContext, times(1)).accumulateFilter(same(givenFilter));
    }

    @Test
    public void additionalValuesShouldBeAccumulated() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);

        this.tagHandler.accumulateAdditionalValues(givenContext);
    }

}
