package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.interim;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.interim.InterimFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.FilterFactoryDictionary;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public abstract class InterimFilterFactoryDictionary<F extends InterimFilterFactory<?>>
        extends FilterFactoryDictionary<F> {
    public InterimFilterFactoryDictionary(final Map<String, F> valuesByKeys) {
        super(valuesByKeys);
    }

    //TODO: read from file
//    private static final Map<String, InterimFilterFactory> FILTER_FACTORIES_BY_PROPERTY_NAMES = ofEntries(
//            entry("трактор", TractorRowFilter::new),
//            entry("механизм", MachineryRowFilter::new),
//            entry("количество корпусов", CorpusCountRowFilter::new),
//            entry("удельное сопротивление", SpecificResistanceRowFilter::new)
//    );


}
