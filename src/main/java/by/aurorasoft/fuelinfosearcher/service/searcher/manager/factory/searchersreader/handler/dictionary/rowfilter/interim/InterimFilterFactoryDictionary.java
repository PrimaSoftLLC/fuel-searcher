package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.rowfilter.interim;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.interim.InterimFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.rowfilter.FilterFactoryDictionary;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public abstract class InterimFilterFactoryDictionary<F extends InterimFilterFactory<?>>
        extends FilterFactoryDictionary<F> {

    public InterimFilterFactoryDictionary(final Map<String, F> valuesByKeys) {
        super(valuesByKeys);
    }

}
