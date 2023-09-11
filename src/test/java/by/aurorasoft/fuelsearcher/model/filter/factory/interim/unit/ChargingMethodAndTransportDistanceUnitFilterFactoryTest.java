package by.aurorasoft.fuelsearcher.model.filter.factory.interim.unit;

import by.aurorasoft.fuelsearcher.model.filter.interim.unit.ChargingMethodAndTransportDistanceUnitFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.ChargingMethodAndTransportDistanceExtractor;
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
                givenChargingMethodAndTransportDistanceExtractor, givenFiltrationCellIndex
        );

        assertSame(givenChargingMethodAndTransportDistanceExtractor, actual.getFiltrationValueExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }
}
