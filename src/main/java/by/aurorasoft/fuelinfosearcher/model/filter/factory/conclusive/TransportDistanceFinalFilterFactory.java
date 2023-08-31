package by.aurorasoft.fuelinfosearcher.model.filter.factory.conclusive;

import by.aurorasoft.fuelinfosearcher.model.filter.conclusive.TransportDistanceFinalFilter;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.TransportDistanceExtractor;
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
