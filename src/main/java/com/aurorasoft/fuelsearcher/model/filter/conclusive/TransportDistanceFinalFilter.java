package com.aurorasoft.fuelsearcher.model.filter.conclusive;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.TransportDistanceExtractor;

public final class TransportDistanceFinalFilter extends FinalFilter {

    public TransportDistanceFinalFilter(final TransportDistanceExtractor transportDistanceExtractor,
                                        final int filtrationCellIndex) {
        super(transportDistanceExtractor, filtrationCellIndex);
    }

}
