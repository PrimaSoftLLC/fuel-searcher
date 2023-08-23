package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractCombine;

public final class CombineRowFilter extends AbstractUnitedRowFilter {

    public CombineRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFilteringValue(final FuelSpecification specification) {
        return extractCombine(specification);
    }

}
