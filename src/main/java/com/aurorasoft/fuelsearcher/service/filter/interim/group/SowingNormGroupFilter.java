package com.aurorasoft.fuelsearcher.service.filter.interim.group;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SowingNormExtractor;

public final class SowingNormGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "Норма высева (семян )?\\d+(-\\d+)? кг/га";

    public SowingNormGroupFilter(final SowingNormExtractor sowingNormExtractor, final int filtrationCellIndex) {
        super(sowingNormExtractor, filtrationCellIndex, GROUP_VALUE_REGEX);
    }
}
