package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.rowfilter;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.FinalFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.conclusive.*;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Component
public final class FinalFilterFactoryDictionary
        extends FilterFactoryDictionary<FinalFilterFactory> {

    //TODO: read from file
    private static final Map<String, FinalFilterFactory> FILTER_FACTORIES_BY_PROPERTY_NAMES = ofEntries(
            entry("глубина вспашки", PloughingDepthRowFilter::new),
            entry("ширина захвата", WorkingWidthRowFilter::new),
            entry("урожайность", YieldRowFilter::new),
            entry("норма внесения", SpreadRateRowFilter::new),
            entry("расстояние транспортировки", TransportDistanceRowFilter::new)
    );

    public FinalFilterFactoryDictionary() {
        super(FILTER_FACTORIES_BY_PROPERTY_NAMES);
    }
}
