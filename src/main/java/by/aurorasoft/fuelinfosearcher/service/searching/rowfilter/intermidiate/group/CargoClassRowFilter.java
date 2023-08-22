package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractCargoClass;

public final class CargoClassRowFilter extends AbstractGroupRowFilter {
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
