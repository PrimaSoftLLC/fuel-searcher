package com.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata;

import com.aurorasoft.fuelsearcher.model.header.SpreadRateHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpreadRateExtractor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public final class SpreadRateHeaderMetadataFactory
        extends FuelHeaderMetadataFactory<SpreadRateExtractor, SpreadRateHeaderMetadata> {

    public SpreadRateHeaderMetadataFactory(final SpreadRateExtractor spreadRateExtractor) {
        super(spreadRateExtractor);
    }

    @Override
    protected SpreadRateHeaderMetadata create(final SpreadRateExtractor spreadRateExtractor,
                                              final Map<String, Integer> fuelOffsetsByHeaders) {
        return new SpreadRateHeaderMetadata(spreadRateExtractor, fuelOffsetsByHeaders);
    }
}
