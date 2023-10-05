package com.aurorasoft.fuelsearcher.service.filter.interim.group;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificResistanceExtractor;

public final class SpecificResistanceGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "Удельное сопротивление (плуга )?\\d+\\.{3}\\d+ кПа";

    public SpecificResistanceGroupFilter(final SpecificResistanceExtractor specificResistanceExtractor,
                                         final int filtrationCellIndex) {
        super(specificResistanceExtractor, filtrationCellIndex, GROUP_VALUE_REGEX);
    }
}
