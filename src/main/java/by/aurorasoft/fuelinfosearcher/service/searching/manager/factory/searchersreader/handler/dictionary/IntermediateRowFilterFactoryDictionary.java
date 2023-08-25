package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary;

import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.AbstractIntermediateRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.CorpusCountRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.MachineryRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.TractorRowFilter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.IntFunction;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Component
public final class IntermediateRowFilterFactoryDictionary extends AbstractDictionary<IntFunction<AbstractIntermediateRowFilter>> {
    private static final Map<String, IntFunction<AbstractIntermediateRowFilter>> FILTER_FACTORIES_BY_PROPERTY_NAMES = ofEntries(
            entry("трактор", TractorRowFilter::new),
            entry("механизм", MachineryRowFilter::new),
            entry("количество корпусов", CorpusCountRowFilter::new)
    );

    public IntermediateRowFilterFactoryDictionary() {
        super(FILTER_FACTORIES_BY_PROPERTY_NAMES);
    }
}
