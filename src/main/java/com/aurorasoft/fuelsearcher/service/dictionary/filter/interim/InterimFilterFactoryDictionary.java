package com.aurorasoft.fuelsearcher.service.dictionary.filter.interim;

import com.aurorasoft.fuelsearcher.service.dictionary.filter.FilterFactoryDictionary;
import com.aurorasoft.fuelsearcher.service.factory.filter.interim.InterimFilterFactory;

import java.util.List;

public abstract class InterimFilterFactoryDictionary<F extends InterimFilterFactory<?, ?>>
        extends FilterFactoryDictionary<F> {

    public InterimFilterFactoryDictionary(final List<F> factories) {
        super(factories);
    }

}
