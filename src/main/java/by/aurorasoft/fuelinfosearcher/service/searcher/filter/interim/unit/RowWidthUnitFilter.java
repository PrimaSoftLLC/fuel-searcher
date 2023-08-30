package by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.unit;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractRowWidth;

public final class RowWidthUnitFilter extends UnitFilter {

    public RowWidthUnitFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractRowWidth(specification);
    }
}
