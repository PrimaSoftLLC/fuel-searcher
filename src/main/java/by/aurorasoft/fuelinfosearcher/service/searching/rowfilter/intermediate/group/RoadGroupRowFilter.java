package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.group;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractRoadGroup;

public final class RoadGroupRowFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "((Первая)|(Вторая)|(Третья)) группа дорог";

    @Override
    protected String extractFilteringValue(final Specification specification) {
        return extractRoadGroup(specification);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}
