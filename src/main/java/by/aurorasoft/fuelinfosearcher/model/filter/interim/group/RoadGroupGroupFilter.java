package by.aurorasoft.fuelinfosearcher.model.filter.interim.group;

import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.RoadGroupExtractor;

public final class RoadGroupGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "((Первая)|(Вторая)|(Третья)) группа дорог";

    public RoadGroupGroupFilter(final RoadGroupExtractor roadGroupExtractor, final int filtrationCellIndex) {
        super(roadGroupExtractor, filtrationCellIndex);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}
