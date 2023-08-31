package by.aurorasoft.fuelinfosearcher.model.filter.interim.group;

import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.SowingNormExtractor;

public final class SowingNormGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "Норма высева (семян )?\\d+(-\\d+)? кг/га";

    public SowingNormGroupFilter(final SowingNormExtractor sowingNormExtractor, final int filtrationCellIndex) {
        super(sowingNormExtractor, filtrationCellIndex);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}
