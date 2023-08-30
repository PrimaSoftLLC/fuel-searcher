package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.united;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractCombine;

public final class CombineRowFilter extends UnitedFilter {

    public CombineRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractCombine(specification);
    }

}
