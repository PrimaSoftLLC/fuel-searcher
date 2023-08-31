package by.aurorasoft.fuelinfosearcher.model.filter.interim.group;

import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.SpecificResistanceExtractor;

public final class SpecificResistanceGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "Удельное сопротивление (плуга )?\\d+...\\d+ кПа";

    public SpecificResistanceGroupFilter(final SpecificResistanceExtractor specificResistanceExtractor,
                                         final int filtrationCellIndex) {
        super(specificResistanceExtractor, filtrationCellIndex);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}
