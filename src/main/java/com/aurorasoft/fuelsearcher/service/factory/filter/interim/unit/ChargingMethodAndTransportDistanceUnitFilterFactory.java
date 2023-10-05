package com.aurorasoft.fuelsearcher.service.factory.filter.interim.unit;

import com.aurorasoft.fuelsearcher.service.filter.interim.unit.ChargingMethodAndTransportDistanceUnitFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.ChargingMethodAndTransportDistanceExtractor;
import org.springframework.stereotype.Component;

@Component
public final class ChargingMethodAndTransportDistanceUnitFilterFactory
        extends UnitFilterFactory<
        ChargingMethodAndTransportDistanceUnitFilter,
        ChargingMethodAndTransportDistanceExtractor> {

    public ChargingMethodAndTransportDistanceUnitFilterFactory(
            final ChargingMethodAndTransportDistanceExtractor chargingMethodAndTransportDistanceExtractor
    ) {
        super(chargingMethodAndTransportDistanceExtractor);
    }

    @Override
    protected ChargingMethodAndTransportDistanceUnitFilter create(
            final ChargingMethodAndTransportDistanceExtractor chargingMethodAndTransportDistanceExtractor,
            final int filtrationCellIndex
    ) {
        return new ChargingMethodAndTransportDistanceUnitFilter(
                chargingMethodAndTransportDistanceExtractor,
                filtrationCellIndex
        );
    }
}
