package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple.filter.interim;

import by.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.junit.Test;

import java.util.OptionalInt;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public final class UnitFilterTagHandlerTest {
    private final UnitFilterTagHandler tagHandler = new UnitFilterTagHandler(null);

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
    public void additionalValuesShouldBeAccumulated() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);

        this.tagHandler.accumulateAdditionalValues(givenContext);
    }
}
