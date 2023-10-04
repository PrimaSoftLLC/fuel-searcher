package com.aurorasoft.fuelsearcher.service.dictionary;

import com.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class FuelHeaderMetadataDictionary extends Dictionary<FuelHeaderMetadata> {

    public FuelHeaderMetadataDictionary(final List<FuelHeaderMetadata> metadata) {
        super(metadata);
    }

}
