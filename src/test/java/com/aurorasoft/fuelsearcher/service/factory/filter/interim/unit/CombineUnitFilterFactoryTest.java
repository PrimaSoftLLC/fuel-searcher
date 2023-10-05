package com.aurorasoft.fuelsearcher.service.factory.filter.interim.unit;

import com.aurorasoft.fuelsearcher.service.filter.interim.unit.CombineUnitFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.CombineExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class CombineUnitFilterFactoryTest {
    private final CombineUnitFilterFactory factory = new CombineUnitFilterFactory(null);

    @Test
    public void filterShouldBeCreated() {
        final CombineExtractor givenCombineExtractor = mock(CombineExtractor.class);
        final int givenFiltrationCellIndex = 5;

        final CombineUnitFilter actual = this.factory.create(givenCombineExtractor, givenFiltrationCellIndex);

        assertSame(givenCombineExtractor, actual.getPropertyExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }
}
