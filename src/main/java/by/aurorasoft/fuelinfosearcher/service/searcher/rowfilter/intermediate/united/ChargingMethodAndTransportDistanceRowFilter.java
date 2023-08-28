package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.united;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractChargingMethodAndTransportDistance;

public final class ChargingMethodAndTransportDistanceRowFilter extends UnitedFilter {

    public ChargingMethodAndTransportDistanceRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFilteringValue(final Specification specification) {
        return extractChargingMethodAndTransportDistance(specification);
    }
}
