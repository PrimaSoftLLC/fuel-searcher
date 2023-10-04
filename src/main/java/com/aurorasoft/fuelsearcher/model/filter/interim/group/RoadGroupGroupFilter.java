package com.aurorasoft.fuelsearcher.model.filter.interim.group;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoadGroupExtractor;

public final class RoadGroupGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "((Первая)|(Вторая)|(Третья)) группа дорог";

    public RoadGroupGroupFilter(final RoadGroupExtractor roadGroupExtractor, final int filtrationCellIndex) {
        super(roadGroupExtractor, filtrationCellIndex, GROUP_VALUE_REGEX);
    }
}
