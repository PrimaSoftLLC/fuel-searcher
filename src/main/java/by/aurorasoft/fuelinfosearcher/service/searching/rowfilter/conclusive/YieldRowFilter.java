package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractYield;

public final class YieldRowFilter extends FinalFilter {

    public YieldRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFilteringValue(final FuelSpecification specification) {
        return extractYield(specification);
    }
}
