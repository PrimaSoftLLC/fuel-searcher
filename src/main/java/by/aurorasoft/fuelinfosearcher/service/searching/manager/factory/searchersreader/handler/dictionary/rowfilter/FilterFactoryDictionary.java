package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter;

import by.aurorasoft.fuelinfosearcher.functionalinterface.rowfilterfactory.FilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.AbstractDictionary;

import java.util.Map;

public abstract class FilterFactoryDictionary<F extends FilterFactory<?>>
        extends AbstractDictionary<F> {

    public FilterFactoryDictionary(final Map<String, F> valuesByKeys) {
        super(valuesByKeys);
    }

}
