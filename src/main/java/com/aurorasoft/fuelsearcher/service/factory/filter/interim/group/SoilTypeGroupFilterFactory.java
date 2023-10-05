package com.aurorasoft.fuelsearcher.service.factory.filter.interim.group;

import com.aurorasoft.fuelsearcher.service.filter.interim.group.SoilTypeGroupFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SoilTypeExtractor;
import org.springframework.stereotype.Component;

@Component
public final class SoilTypeGroupFilterFactory extends GroupFilterFactory<SoilTypeGroupFilter, SoilTypeExtractor> {

    public SoilTypeGroupFilterFactory(final SoilTypeExtractor soilTypeExtractor) {
        super(soilTypeExtractor);
    }

    @Override
    protected SoilTypeGroupFilter create(final SoilTypeExtractor soilTypeExtractor, final int filtrationCellIndex) {
        return new SoilTypeGroupFilter(soilTypeExtractor, filtrationCellIndex);
    }
}
