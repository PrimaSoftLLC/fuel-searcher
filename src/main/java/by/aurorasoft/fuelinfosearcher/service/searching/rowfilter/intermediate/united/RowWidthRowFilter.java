package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.united;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractRowWidth;

public final class RowWidthRowFilter extends UnitedFilter {

    public RowWidthRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFilteringValue(final FuelSpecification specification) {
        return extractRowWidth(specification);
    }
}
