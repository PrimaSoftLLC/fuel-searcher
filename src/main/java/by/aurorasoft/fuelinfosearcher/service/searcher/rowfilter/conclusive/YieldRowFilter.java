package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.conclusive;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractYield;

public final class YieldRowFilter extends FinalFilter {

    public YieldRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractYield(specification);
    }
}
