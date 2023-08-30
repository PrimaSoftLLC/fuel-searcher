package by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.unit;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractCorpusCount;

public final class CorpusCountUnitFilter extends UnitFilter {

    public CorpusCountUnitFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractCorpusCount(specification);
    }
}
