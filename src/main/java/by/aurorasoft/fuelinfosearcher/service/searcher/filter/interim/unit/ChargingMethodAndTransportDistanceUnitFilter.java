package by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.unit;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractChargingMethodAndTransportDistance;

public final class ChargingMethodAndTransportDistanceUnitFilter extends UnitFilter {

    public ChargingMethodAndTransportDistanceUnitFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractChargingMethodAndTransportDistance(specification);
    }
}
