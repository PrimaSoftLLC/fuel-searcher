package com.aurorasoft.fuelsearcher.service.factory.filter.interim.unit;

import com.aurorasoft.fuelsearcher.model.filter.interim.unit.TractorUnitFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.TractorExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class TractorUnitFilterFactoryTest {
    private final TractorUnitFilterFactory factory = new TractorUnitFilterFactory(null);

    @Test
    public void filterShouldBeCreated() {
        final TractorExtractor givenTractorExtractor = mock(TractorExtractor.class);
        final int givenFiltrationCellIndex = 3;

        final TractorUnitFilter actual = this.factory.create(givenTractorExtractor, givenFiltrationCellIndex);

        assertSame(givenTractorExtractor, actual.getPropertyExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }
}
