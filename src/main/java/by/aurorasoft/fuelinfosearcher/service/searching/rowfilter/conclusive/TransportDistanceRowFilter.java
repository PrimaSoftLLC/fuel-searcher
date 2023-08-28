package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractTransportDistance;

public final class TransportDistanceRowFilter extends FinalFilter {

    public TransportDistanceRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFilteringValue(final FuelSpecification specification) {
        return extractTransportDistance(specification);
    }
}
