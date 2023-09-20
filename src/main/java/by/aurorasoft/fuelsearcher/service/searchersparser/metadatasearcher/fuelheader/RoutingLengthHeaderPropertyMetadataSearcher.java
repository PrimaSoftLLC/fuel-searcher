package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher.fuelheader;

import by.aurorasoft.fuelsearcher.model.header.RoutingLengthHeaderMetadata;
import org.springframework.stereotype.Component;

@Component
public final class RoutingLengthHeaderPropertyMetadataSearcher
        extends FuelHeaderPropertyMetadataSearcher<RoutingLengthHeaderMetadata> {

    public RoutingLengthHeaderPropertyMetadataSearcher() {
        super(RoutingLengthHeaderMetadata.class);
    }

}
