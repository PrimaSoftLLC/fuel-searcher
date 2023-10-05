package com.aurorasoft.fuelsearcher.service.factory.filter.interim.unit;

import com.aurorasoft.fuelsearcher.model.filter.interim.unit.MachineryUnitFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.MachineryExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class MachineryUnitFilterFactoryTest {
    private final MachineryUnitFilterFactory factory = new MachineryUnitFilterFactory(null);

    @Test
    public void filterShouldBeCreated() {
        final MachineryExtractor givenMachineryExtractor = mock(MachineryExtractor.class);
        final int givenFiltrationCellIndex = 2;

        final MachineryUnitFilter actual = this.factory.create(givenMachineryExtractor, givenFiltrationCellIndex);

        assertSame(givenMachineryExtractor, actual.getPropertyExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }
}
