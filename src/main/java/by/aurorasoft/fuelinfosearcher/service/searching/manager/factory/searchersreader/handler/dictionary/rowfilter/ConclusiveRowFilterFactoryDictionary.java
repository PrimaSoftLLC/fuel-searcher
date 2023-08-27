package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter;

import by.aurorasoft.fuelinfosearcher.functionalinterface.rowfilterfactory.ConclusiveRowFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.PloughingDepthRowFilter;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Component
public final class ConclusiveRowFilterFactoryDictionary
        extends AbstractRowFilterFactoryDictionary<ConclusiveRowFilterFactory> {

    private static final Map<String, ConclusiveRowFilterFactory> FILTER_FACTORIES_BY_PROPERTY_NAMES = ofEntries(
            entry("глубина вспашки", PloughingDepthRowFilter::new)
    );

    public ConclusiveRowFilterFactoryDictionary() {
        super(FILTER_FACTORIES_BY_PROPERTY_NAMES);
    }
}
