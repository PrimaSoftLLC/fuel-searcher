package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificResistanceExtractor;

public final class SpecificResistanceGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "Удельное сопротивление (плуга )?\\d+...\\d+ кПа";

    public SpecificResistanceGroupFilter(final SpecificResistanceExtractor specificResistanceExtractor,
                                         final int filtrationCellIndex) {
        super(specificResistanceExtractor, filtrationCellIndex, GROUP_VALUE_REGEX);
    }
}
