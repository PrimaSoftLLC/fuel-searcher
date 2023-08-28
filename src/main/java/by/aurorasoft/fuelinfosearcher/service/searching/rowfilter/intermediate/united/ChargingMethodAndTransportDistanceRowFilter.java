package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.united;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractChargingMethodAndTransportDistance;

public final class ChargingMethodAndTransportDistanceRowFilter extends UnitedFilter {

    public ChargingMethodAndTransportDistanceRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFilteringValue(final FuelSpecification specification) {
        return extractChargingMethodAndTransportDistance(specification);
    }
}
