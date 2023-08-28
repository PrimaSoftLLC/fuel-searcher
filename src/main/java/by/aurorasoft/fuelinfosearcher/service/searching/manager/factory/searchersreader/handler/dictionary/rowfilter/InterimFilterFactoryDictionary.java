package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter;

import by.aurorasoft.fuelinfosearcher.functionalinterface.rowfilterfactory.InterimFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.group.SpecificResistanceRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.united.CorpusCountRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.united.MachineryRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.united.TractorRowFilter;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Component
public final class InterimFilterFactoryDictionary
        extends AbstractFilterFactoryDictionary<InterimFilterFactory> {

    //TODO: read from file
    private static final Map<String, InterimFilterFactory> FILTER_FACTORIES_BY_PROPERTY_NAMES = ofEntries(
            entry("трактор", TractorRowFilter::new),
            entry("механизм", MachineryRowFilter::new),
            entry("количество корпусов", CorpusCountRowFilter::new),
            entry("удельное сопротивление", SpecificResistanceRowFilter::new)
    );

    public InterimFilterFactoryDictionary() {
        super(FILTER_FACTORIES_BY_PROPERTY_NAMES);
    }

}
