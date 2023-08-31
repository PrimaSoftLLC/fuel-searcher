package by.aurorasoft.fuelinfosearcher.service.searcher.filter.conclusive;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractWorkingWidth;

public final class WorkingWidthFinalFilter extends FinalFilter {

    public WorkingWidthFinalFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractWorkingWidth(specification);
    }

}
