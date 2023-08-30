package by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.united;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractChargingMethodAndTransportDistance;

public final class ChargingMethodAndTransportDistanceRowFilter extends UnitedFilter {

    public ChargingMethodAndTransportDistanceRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractChargingMethodAndTransportDistance(specification);
    }
}
