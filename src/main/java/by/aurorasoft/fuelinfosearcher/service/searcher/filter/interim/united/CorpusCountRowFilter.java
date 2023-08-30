package by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.united;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractCorpusCount;

public final class CorpusCountRowFilter extends UnitedFilter {

    public CorpusCountRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractCorpusCount(specification);
    }
}
