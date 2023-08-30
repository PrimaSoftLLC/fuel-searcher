package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.group;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractCargoClass;

public final class CargoClassRowFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "Грузы (I|II|III|IV) класса";

    public CargoClassRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFiltrationValue(final Specification specification) {
        return extractCargoClass(specification);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }
}
