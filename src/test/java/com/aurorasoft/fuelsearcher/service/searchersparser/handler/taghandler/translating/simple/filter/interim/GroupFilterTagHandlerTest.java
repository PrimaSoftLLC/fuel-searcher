package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple.filter.interim;

import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.junit.Test;

import java.util.OptionalInt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public final class GroupFilterTagHandlerTest {
    private static final int EXPECTED_DEFAULT_FILTRATION_CELL_INDEX = 0;

    private final GroupFilterTagHandler tagHandler = new GroupFilterTagHandler(null);

    @Test
    public void startTagShouldBeHandled() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);

        this.tagHandler.handleStartTag(givenContext);
    }

    @Test
    public void defaultFiltrationCellIndexShouldBeFound() {
        final OptionalInt optionalActual = this.tagHandler.findDefaultFiltrationCellIndex();
        assertTrue(optionalActual.isPresent());
        final int actual = optionalActual.getAsInt();
        assertEquals(EXPECTED_DEFAULT_FILTRATION_CELL_INDEX, actual);
    }

    @Test
    public void additionalValuesShouldBeAccumulated() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);

        this.tagHandler.accumulateAdditionalValues(givenContext);
    }
}
