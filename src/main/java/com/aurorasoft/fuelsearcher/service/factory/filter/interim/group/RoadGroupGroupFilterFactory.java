package com.aurorasoft.fuelsearcher.service.factory.filter.interim.group;

import com.aurorasoft.fuelsearcher.model.filter.interim.group.RoadGroupGroupFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoadGroupExtractor;
import org.springframework.stereotype.Component;

@Component
public final class RoadGroupGroupFilterFactory extends GroupFilterFactory<RoadGroupGroupFilter, RoadGroupExtractor> {

    public RoadGroupGroupFilterFactory(final RoadGroupExtractor roadGroupExtractor) {
        super(roadGroupExtractor);
    }

    @Override
    protected RoadGroupGroupFilter create(final RoadGroupExtractor roadGroupExtractor, final int filtrationCellIndex) {
        return new RoadGroupGroupFilter(roadGroupExtractor, filtrationCellIndex);
    }
}
