package by.aurorasoft.fuelinfosearcher.model.filter.interim.unit;

import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.ChargingMethodAndTransportDistanceExtractor;

public final class ChargingMethodAndTransportDistanceUnitFilter extends UnitFilter {

    public ChargingMethodAndTransportDistanceUnitFilter(
            final ChargingMethodAndTransportDistanceExtractor chargingMethodAndTransportDistanceExtractor,
            final int filtrationCellIndex) {
        super(chargingMethodAndTransportDistanceExtractor, filtrationCellIndex);
    }
}
