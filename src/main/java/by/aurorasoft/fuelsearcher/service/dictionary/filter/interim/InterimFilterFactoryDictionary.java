package by.aurorasoft.fuelsearcher.service.dictionary.filter.interim;

import by.aurorasoft.fuelsearcher.service.dictionary.filter.FilterFactoryDictionary;
import by.aurorasoft.fuelsearcher.model.filter.factory.interim.InterimFilterFactory;

import java.util.List;

public abstract class InterimFilterFactoryDictionary<F extends InterimFilterFactory<?, ?>>
        extends FilterFactoryDictionary<F> {

    public InterimFilterFactoryDictionary(final List<F> filterFactories) {
        super(filterFactories);
    }

}
