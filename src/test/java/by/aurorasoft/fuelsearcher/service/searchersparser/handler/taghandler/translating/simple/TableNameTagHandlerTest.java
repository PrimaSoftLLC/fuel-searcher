package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.junit.Test;

import static org.mockito.Mockito.*;

public final class TableNameTagHandlerTest {
    private final TableNameTagHandler tagHandler = new TableNameTagHandler(null);

    @Test
    public void startTagShouldBeHandled() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);

        this.tagHandler.handleStartTag(givenContext);
    }

    @Test
    public void translatedValueShouldBeAccumulated() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);
        final FuelTable givenFuelTable = mock(FuelTable.class);

        this.tagHandler.accumulateTranslatedValue(givenContext, givenFuelTable);

        verify(givenContext, times(1)).accumulateFuelTable(same(givenFuelTable));
    }

    @Test
    public void additionalValuesShouldBeAccumulated() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);

        this.tagHandler.accumulateAdditionalValues(givenContext);
    }
}
