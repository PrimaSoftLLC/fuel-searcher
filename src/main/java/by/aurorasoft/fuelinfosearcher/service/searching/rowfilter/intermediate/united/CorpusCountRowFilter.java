package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.united;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractCorpusCount;

public final class CorpusCountRowFilter extends UnitedFilter {

    public CorpusCountRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFilteringValue(final FuelSpecification specification) {
        return extractCorpusCount(specification);
    }
}
