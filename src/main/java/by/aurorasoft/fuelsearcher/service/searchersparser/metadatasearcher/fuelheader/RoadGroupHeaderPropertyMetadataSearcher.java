package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher.fuelheader;

import by.aurorasoft.fuelsearcher.model.header.RoadGroupHeaderMetadata;
import org.springframework.stereotype.Component;

@Component
public final class RoadGroupHeaderPropertyMetadataSearcher
        extends FuelHeaderPropertyMetadataSearcher<RoadGroupHeaderMetadata> {

    public RoadGroupHeaderPropertyMetadataSearcher() {
        super(RoadGroupHeaderMetadata.class);
    }

}
