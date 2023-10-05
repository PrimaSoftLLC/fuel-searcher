package com.aurorasoft.fuelsearcher.service.factory.filter.interim.unit;

import com.aurorasoft.fuelsearcher.service.filter.interim.unit.ChargingMethodAndTransportDistanceUnitFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.ChargingMethodAndTransportDistanceExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class ChargingMethodAndTransportDistanceUnitFilterFactoryTest {
    private final ChargingMethodAndTransportDistanceUnitFilterFactory factory
            = new ChargingMethodAndTransportDistanceUnitFilterFactory(null);

    @Test
    public void filterShouldBeCreated() {
        final ChargingMethodAndTransportDistanceExtractor givenChargingMethodAndTransportDistanceExtractor = mock(
                ChargingMethodAndTransportDistanceExtractor.class
        );
        final int givenFiltrationCellIndex = 2;

        final ChargingMethodAndTransportDistanceUnitFilter actual = this.factory.create(
                givenChargingMethodAndTransportDistanceExtractor,
                givenFiltrationCellIndex
        );

        assertSame(givenChargingMethodAndTransportDistanceExtractor, actual.getPropertyExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }
}
