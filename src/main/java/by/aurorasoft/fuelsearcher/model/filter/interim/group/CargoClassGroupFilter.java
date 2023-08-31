package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.CargoClassExtractor;

public final class CargoClassGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "Грузы (I|II|III|IV) класса";

    public CargoClassGroupFilter(final CargoClassExtractor cargoClassExtractor, final int filtrationCellIndex) {
        super(cargoClassExtractor, filtrationCellIndex);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }
}
