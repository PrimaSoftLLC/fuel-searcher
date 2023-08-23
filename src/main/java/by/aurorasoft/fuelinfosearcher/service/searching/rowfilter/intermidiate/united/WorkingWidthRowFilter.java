package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractWorkingWidth;

public final class WorkingWidthRowFilter extends AbstractUnitedRowFilter {

    public WorkingWidthRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFilteringValue(final FuelSpecification specification) {
        return extractWorkingWidth(specification);
    }
}