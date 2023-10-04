package com.aurorasoft.fuelsearcher.model.filter.interim.unit;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.ChargingMethodAndTransportDistanceExtractor;

public final class ChargingMethodAndTransportDistanceUnitFilter extends UnitFilter {

    public ChargingMethodAndTransportDistanceUnitFilter(
            final ChargingMethodAndTransportDistanceExtractor chargingMethodAndTransportDistanceExtractor,
            final int filtrationCellIndex
    ) {
        super(chargingMethodAndTransportDistanceExtractor, filtrationCellIndex);
    }
}
