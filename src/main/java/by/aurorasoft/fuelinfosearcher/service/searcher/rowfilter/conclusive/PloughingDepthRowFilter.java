package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.conclusive;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractPloughingDepth;

public final class PloughingDepthRowFilter extends FinalFilter {

    public PloughingDepthRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractPloughingDepth(specification);
    }
}
