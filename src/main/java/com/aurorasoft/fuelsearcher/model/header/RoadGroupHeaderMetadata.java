package com.aurorasoft.fuelsearcher.model.header;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoadGroupExtractor;

import java.util.Map;

public final class RoadGroupHeaderMetadata extends FuelHeaderMetadata {

    public RoadGroupHeaderMetadata(final RoadGroupExtractor roadGroupExtractor,
                                   final Map<String, Integer> fuelOffsetsByValues) {
        super(roadGroupExtractor, fuelOffsetsByValues);
    }

}
