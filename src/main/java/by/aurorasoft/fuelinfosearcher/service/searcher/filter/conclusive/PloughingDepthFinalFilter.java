package by.aurorasoft.fuelinfosearcher.service.searcher.filter.conclusive;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractPloughingDepth;

public final class PloughingDepthFinalFilter extends FinalFilter {

    public PloughingDepthFinalFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractPloughingDepth(specification);
    }
}
