package by.aurorasoft.fuelinfosearcher.model.filter.conclusive;

import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.TransportDistanceExtractor;

public final class TransportDistanceFinalFilter extends FinalFilter {

    public TransportDistanceFinalFilter(final TransportDistanceExtractor transportDistanceExtractor,
                                        final int filtrationCellIndex) {
        super(transportDistanceExtractor, filtrationCellIndex);
    }

}
