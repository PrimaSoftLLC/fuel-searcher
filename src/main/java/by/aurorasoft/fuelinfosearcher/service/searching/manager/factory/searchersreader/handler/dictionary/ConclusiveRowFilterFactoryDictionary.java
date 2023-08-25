package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary;

import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.AbstractConclusiveRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.PloughingDepthRowFilter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.IntFunction;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Component
public final class ConclusiveRowFilterFactoryDictionary extends AbstractDictionary<IntFunction<AbstractConclusiveRowFilter>> {
    private static final Map<String, IntFunction<AbstractConclusiveRowFilter>> FILTER_FACTORIES_BY_PROPERTY_NAMES = ofEntries(
            entry("глубина вспашки", PloughingDepthRowFilter::new)
    );

    public ConclusiveRowFilterFactoryDictionary() {
        super(FILTER_FACTORIES_BY_PROPERTY_NAMES);
    }
}
