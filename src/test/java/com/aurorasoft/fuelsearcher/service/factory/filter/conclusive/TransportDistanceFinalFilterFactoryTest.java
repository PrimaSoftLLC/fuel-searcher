package com.aurorasoft.fuelsearcher.service.factory.filter.conclusive;

import com.aurorasoft.fuelsearcher.model.filter.conclusive.TransportDistanceFinalFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.TransportDistanceExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class TransportDistanceFinalFilterFactoryTest {
    private final TransportDistanceFinalFilterFactory factory = new TransportDistanceFinalFilterFactory(
            null
    );

    @Test
    public void filterShouldBeCreated() {
        final TransportDistanceExtractor givenTransportDistanceExtractor = mock(TransportDistanceExtractor.class);
        final int givenFiltrationCellIndex = 1;

        final TransportDistanceFinalFilter actual = this.factory.create(
                givenTransportDistanceExtractor,
                givenFiltrationCellIndex
        );
        assertSame(givenTransportDistanceExtractor, actual.getPropertyExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }

}
