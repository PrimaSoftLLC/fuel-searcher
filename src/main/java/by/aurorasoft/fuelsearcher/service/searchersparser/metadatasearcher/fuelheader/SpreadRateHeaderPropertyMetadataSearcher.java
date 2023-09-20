package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher.fuelheader;

import by.aurorasoft.fuelsearcher.model.header.SpreadRateHeaderMetadata;
import org.springframework.stereotype.Component;

@Component
public final class SpreadRateHeaderPropertyMetadataSearcher
        extends FuelHeaderPropertyMetadataSearcher<SpreadRateHeaderMetadata> {

    public SpreadRateHeaderPropertyMetadataSearcher() {
        super(SpreadRateHeaderMetadata.class);
    }

}
