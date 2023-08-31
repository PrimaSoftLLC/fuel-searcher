package by.aurorasoft.fuelinfosearcher.model.filter.interim.group;

import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.SoilTypeExtractor;

public final class SoilTypeGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "(Минеральные почвы)|(Торфяные почвы)|(Легкие почвы)|(Средние почвы)|(Тяжелые почвы)";

    public SoilTypeGroupFilter(final SoilTypeExtractor soilTypeExtractor, final int filtrationCellIndex) {
        super(soilTypeExtractor, filtrationCellIndex);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}
