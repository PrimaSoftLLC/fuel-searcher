package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.united;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractMachinery;

public final class MachineryRowFilter extends UnitedFilter {

    public MachineryRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractMachinery(specification);
    }
}
