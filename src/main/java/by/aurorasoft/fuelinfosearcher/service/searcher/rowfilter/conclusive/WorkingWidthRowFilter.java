package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.conclusive;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractWorkingWidth;

public final class WorkingWidthRowFilter extends FinalFilter {

    public WorkingWidthRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractWorkingWidth(specification);
    }

}
