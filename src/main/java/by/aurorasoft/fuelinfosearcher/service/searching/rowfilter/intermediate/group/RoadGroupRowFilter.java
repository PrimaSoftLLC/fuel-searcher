package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.group;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractRoadGroup;

public final class RoadGroupRowFilter extends AbstractGroupRowFilter {
    private static final String GROUP_VALUE_REGEX = "((Первая)|(Вторая)|(Третья)) группа дорог";

    @Override
    protected String extractFilteringValue(final FuelSpecification specification) {
        return extractRoadGroup(specification);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}
