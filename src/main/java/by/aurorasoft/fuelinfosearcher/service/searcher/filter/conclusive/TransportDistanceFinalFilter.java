package by.aurorasoft.fuelinfosearcher.service.searcher.filter.conclusive;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractTransportDistance;

public final class TransportDistanceFinalFilter extends FinalFilter {

    public TransportDistanceFinalFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractTransportDistance(specification);
    }
}
