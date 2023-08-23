package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractMachinery;

public final class MachineryRowFilter extends AbstractUnitedRowFilter {

    public MachineryRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFilteringValue(final FuelSpecification specification) {
        return extractMachinery(specification);
    }
}
