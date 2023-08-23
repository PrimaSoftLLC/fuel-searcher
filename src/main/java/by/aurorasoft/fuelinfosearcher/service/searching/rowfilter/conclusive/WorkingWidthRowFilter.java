package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractWorkingWidth;

public final class WorkingWidthRowFilter extends AbstractConclusiveRowFilter {

    public WorkingWidthRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFilteringValue(final FuelSpecification specification) {
        return extractWorkingWidth(specification);
    }

}
