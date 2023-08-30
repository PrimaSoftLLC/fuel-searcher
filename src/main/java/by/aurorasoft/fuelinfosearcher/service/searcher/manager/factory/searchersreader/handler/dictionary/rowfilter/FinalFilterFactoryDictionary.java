package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.rowfilter;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.FinalFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searcher.filter.conclusive.*;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Component
public final class FinalFilterFactoryDictionary
        extends FilterFactoryDictionary<FinalFilterFactory> {

    //TODO: read from file
    private static final Map<String, FinalFilterFactory> FILTER_FACTORIES_BY_PROPERTY_NAMES = ofEntries(
            entry("глубина вспашки", PloughingDepthFinalFilter::new),
            entry("ширина захвата", WorkingWidthFinalFilter::new),
            entry("урожайность", YieldFinalFilter::new),
            entry("норма внесения", SpreadRateFinalFilter::new),
            entry("расстояние транспортировки", TransportDistanceFinalFilter::new)
    );

    public FinalFilterFactoryDictionary() {
        super(FILTER_FACTORIES_BY_PROPERTY_NAMES);
    }
}
