package by.aurorasoft.fuelsearcher.model.filter.factory.interim.unit;

import by.aurorasoft.fuelsearcher.model.filter.interim.unit.ChargingMethodAndTransportDistanceUnitFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.ChargingMethodAndTransportDistanceExtractor;
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
