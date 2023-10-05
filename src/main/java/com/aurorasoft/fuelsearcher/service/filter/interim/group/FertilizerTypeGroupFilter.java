package com.aurorasoft.fuelsearcher.service.filter.interim.group;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.FertilizerTypeExtractor;

public final class FertilizerTypeGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "((Гранулированные)|(Кристаллические)|(Пылевидные)) удобрения";

    public FertilizerTypeGroupFilter(final FertilizerTypeExtractor fertilizerTypeExtractor,
                                     final int filtrationCellIndex) {
        super(fertilizerTypeExtractor, filtrationCellIndex, GROUP_VALUE_REGEX);
    }
}
