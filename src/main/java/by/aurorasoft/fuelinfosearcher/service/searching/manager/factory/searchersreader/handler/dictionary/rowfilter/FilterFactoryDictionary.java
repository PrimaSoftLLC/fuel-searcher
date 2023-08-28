package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.FilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.Dictionary;

import java.util.Map;

public abstract class FilterFactoryDictionary<F extends FilterFactory<?>>
        extends Dictionary<F> {

    public FilterFactoryDictionary(final Map<String, F> valuesByKeys) {
        super(valuesByKeys);
    }

}
