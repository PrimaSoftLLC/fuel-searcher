package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.FertilizerTypeExtractor;

public final class FertilizerTypeGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "((Гранулированные)|(Кристаллические)|(Пылевидные)) удобрения";

    public FertilizerTypeGroupFilter(final FertilizerTypeExtractor fertilizerTypeExtractor,
                                     final int filtrationCellIndex) {
        super(fertilizerTypeExtractor, filtrationCellIndex);
    }

    @Override
    public String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }
}
