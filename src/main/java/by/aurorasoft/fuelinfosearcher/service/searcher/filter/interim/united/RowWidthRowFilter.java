package by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.united;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractRowWidth;

public final class RowWidthRowFilter extends UnitedFilter {

    public RowWidthRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractRowWidth(specification);
    }
}
