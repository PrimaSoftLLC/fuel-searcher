package com.aurorasoft.fuelsearcher.service.factory.filter.conclusive;

import com.aurorasoft.fuelsearcher.service.filter.conclusive.TransportDistanceFinalFilter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.TransportDistanceExtractor;
import org.springframework.stereotype.Component;

@Component
public final class TransportDistanceFinalFilterFactory
        extends FinalFilterFactory<TransportDistanceFinalFilter, TransportDistanceExtractor> {

    public TransportDistanceFinalFilterFactory(final TransportDistanceExtractor transportDistanceExtractor) {
        super(transportDistanceExtractor);
    }

    @Override
    protected TransportDistanceFinalFilter create(final TransportDistanceExtractor transportDistanceExtractor,
                                                  final int filtrationCellIndex) {
        return new TransportDistanceFinalFilter(transportDistanceExtractor, filtrationCellIndex);
    }
}
