package com.aurorasoft.fuelsearcher.model.filter.factory.interim.unit;

import com.aurorasoft.fuelsearcher.model.filter.interim.unit.RowWidthUnitFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.RowWidthExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class RowWidthUnitFilterFactoryTest {
    private final RowWidthUnitFilterFactory factory = new RowWidthUnitFilterFactory(null);

    @Test
    public void filterShouldBeCreated() {
        final RowWidthExtractor givenRowWidthExtractor = mock(RowWidthExtractor.class);
        final int givenFiltrationCellIndex = 2;

        final RowWidthUnitFilter actual = this.factory.create(givenRowWidthExtractor, givenFiltrationCellIndex);

        assertSame(givenRowWidthExtractor, actual.getPropertyExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }
}
