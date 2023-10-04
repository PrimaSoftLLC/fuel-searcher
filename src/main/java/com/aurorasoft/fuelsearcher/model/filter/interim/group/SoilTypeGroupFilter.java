package com.aurorasoft.fuelsearcher.model.filter.interim.group;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SoilTypeExtractor;

public final class SoilTypeGroupFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "((Минеральные)|(Торфяные)|(Легкие)|(Средние)|(Тяжелые)) почвы";

    public SoilTypeGroupFilter(final SoilTypeExtractor soilTypeExtractor, final int filtrationCellIndex) {
        super(soilTypeExtractor, filtrationCellIndex, GROUP_VALUE_REGEX);
    }
}
