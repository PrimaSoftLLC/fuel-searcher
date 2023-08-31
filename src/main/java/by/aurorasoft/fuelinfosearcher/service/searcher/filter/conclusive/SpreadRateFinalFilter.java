package by.aurorasoft.fuelinfosearcher.service.searcher.filter.conclusive;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractSpreadRate;

public final class SpreadRateFinalFilter extends FinalFilter {

    public SpreadRateFinalFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractSpreadRate(specification);
    }
}
