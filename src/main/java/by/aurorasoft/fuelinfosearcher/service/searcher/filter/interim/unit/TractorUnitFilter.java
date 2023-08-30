package by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.unit;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractTractor;

public final class TractorUnitFilter extends UnitFilter {

    public TractorUnitFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractTractor(specification);
    }
}
