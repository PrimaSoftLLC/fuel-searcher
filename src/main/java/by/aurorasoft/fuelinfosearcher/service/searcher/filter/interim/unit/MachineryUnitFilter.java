package by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.unit;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractMachinery;

public final class MachineryUnitFilter extends UnitFilter {

    public MachineryUnitFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractMachinery(specification);
    }
}
