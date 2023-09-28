package by.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata;

import by.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.header.RoadGroupHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoadGroupExtractor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public final class RoadGroupHeaderMetadataFactory extends FuelHeaderMetadataFactory<RoadGroupExtractor> {

    public RoadGroupHeaderMetadataFactory(final RoadGroupExtractor roadGroupExtractor) {
        super(roadGroupExtractor);
    }

    @Override
    protected FuelHeaderMetadata create(final RoadGroupExtractor roadGroupExtractor,
                                        final Map<String, Integer> fuelOffsetsByHeaders) {
        return new RoadGroupHeaderMetadata(roadGroupExtractor, fuelOffsetsByHeaders);
    }
}
