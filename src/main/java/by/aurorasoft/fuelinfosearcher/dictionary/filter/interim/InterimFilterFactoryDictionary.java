package by.aurorasoft.fuelinfosearcher.dictionary.filter.interim;

import by.aurorasoft.fuelinfosearcher.dictionary.filter.FilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.model.filter.factory.interim.InterimFilterFactory;

import java.util.List;

public abstract class InterimFilterFactoryDictionary<F extends InterimFilterFactory<?, ?>>
        extends FilterFactoryDictionary<F> {

    public InterimFilterFactoryDictionary(final List<F> filterFactories) {
        super(filterFactories);
    }

}
