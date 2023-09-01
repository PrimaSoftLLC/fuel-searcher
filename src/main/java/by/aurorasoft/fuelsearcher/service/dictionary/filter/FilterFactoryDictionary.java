package by.aurorasoft.fuelsearcher.service.dictionary.filter;

import by.aurorasoft.fuelsearcher.service.dictionary.Dictionary;
import by.aurorasoft.fuelsearcher.model.filter.factory.FilterFactory;

import java.util.List;

public abstract class FilterFactoryDictionary<F extends FilterFactory<?, ?>> extends Dictionary<F> {

    public FilterFactoryDictionary(final List<F> filterFactories) {
        super(filterFactories);
    }

}