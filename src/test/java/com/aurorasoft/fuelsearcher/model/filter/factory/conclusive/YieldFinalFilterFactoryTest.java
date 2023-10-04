package com.aurorasoft.fuelsearcher.model.filter.factory.conclusive;

import com.aurorasoft.fuelsearcher.model.filter.conclusive.YieldFinalFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.YieldExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class YieldFinalFilterFactoryTest {
    private final YieldFinalFilterFactory factory = new YieldFinalFilterFactory(null);

    @Test
    public void filterShouldBeCreated() {
        final YieldExtractor givenYieldExtractor = mock(YieldExtractor.class);
        final int givenFiltrationCellIndex = 6;

        final YieldFinalFilter actual = this.factory.create(givenYieldExtractor, givenFiltrationCellIndex);
        assertSame(givenYieldExtractor, actual.getPropertyExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }
}
