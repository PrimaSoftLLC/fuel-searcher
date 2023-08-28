package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractPloughingDepth;

public final class PloughingDepthRowFilter extends FinalFilter {

    public PloughingDepthRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFilteringValue(final FuelSpecification specification) {
        return extractPloughingDepth(specification);
    }
}
