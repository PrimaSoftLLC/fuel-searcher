package com.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata;

import com.aurorasoft.fuelsearcher.model.header.RoadGroupHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoadGroupExtractor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public final class RoadGroupHeaderMetadataFactory
        extends FuelHeaderMetadataFactory<RoadGroupExtractor, RoadGroupHeaderMetadata> {

    public RoadGroupHeaderMetadataFactory(final RoadGroupExtractor roadGroupExtractor) {
        super(roadGroupExtractor);
    }

    @Override
    protected RoadGroupHeaderMetadata create(final RoadGroupExtractor roadGroupExtractor,
                                             final Map<String, Integer> fuelOffsetsByHeaders) {
        return new RoadGroupHeaderMetadata(roadGroupExtractor, fuelOffsetsByHeaders);
    }
}
