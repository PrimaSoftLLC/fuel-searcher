package by.aurorasoft.fuelinfosearcher.dictionary.filter;

import by.aurorasoft.fuelinfosearcher.dictionary.Dictionary;
import by.aurorasoft.fuelinfosearcher.model.filter.factory.FilterFactory;

import java.util.List;

public abstract class FilterFactoryDictionary<F extends FilterFactory<?, ?>> extends Dictionary<F> {

    public FilterFactoryDictionary(final List<F> filterFactories) {
        super(filterFactories);
    }

}
