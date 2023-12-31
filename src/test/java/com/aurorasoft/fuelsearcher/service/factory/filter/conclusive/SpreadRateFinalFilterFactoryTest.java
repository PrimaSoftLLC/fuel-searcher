package com.aurorasoft.fuelsearcher.service.factory.filter.conclusive;

import com.aurorasoft.fuelsearcher.service.filter.conclusive.SpreadRateFinalFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpreadRateExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class SpreadRateFinalFilterFactoryTest {
    private final SpreadRateFinalFilterFactory factory = new SpreadRateFinalFilterFactory(null);

    @Test
    public void filterShouldBeCreated() {
        final SpreadRateExtractor givenSpreadRateExtractor = mock(SpreadRateExtractor.class);
        final int givenFiltrationCellIndex = 2;

        final SpreadRateFinalFilter actual = this.factory.create(givenSpreadRateExtractor, givenFiltrationCellIndex);
        assertSame(givenSpreadRateExtractor, actual.getPropertyExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }
}
