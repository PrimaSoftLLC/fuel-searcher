package com.aurorasoft.fuelsearcher.service.factory.filter.interim.group;

import com.aurorasoft.fuelsearcher.model.filter.interim.group.CargoClassGroupFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.CargoClassExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class CargoClassGroupFilterFactoryTest {
    private final CargoClassGroupFilterFactory factory = new CargoClassGroupFilterFactory(null);

    @Test
    public void filterShouldBeCreated() {
        final CargoClassExtractor givenCargoClassExtractor = mock(CargoClassExtractor.class);
        final int givenFiltrationCellIndex = 5;

        final CargoClassGroupFilter actual = this.factory.create(givenCargoClassExtractor, givenFiltrationCellIndex);

        assertSame(givenCargoClassExtractor, actual.getPropertyExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }
}
