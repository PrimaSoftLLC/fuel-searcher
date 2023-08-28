package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.group;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractCargoClass;

public final class CargoClassRowFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "Грузы (I|II|III|IV) класса";

    @Override
    protected String extractFilteringValue(final FuelSpecification specification) {
        return extractCargoClass(specification);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }
}
