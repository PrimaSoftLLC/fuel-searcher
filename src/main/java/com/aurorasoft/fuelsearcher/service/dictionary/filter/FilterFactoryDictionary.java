package com.aurorasoft.fuelsearcher.service.dictionary.filter;

import com.aurorasoft.fuelsearcher.service.dictionary.Dictionary;
import com.aurorasoft.fuelsearcher.service.factory.filter.FilterFactory;

import java.util.List;

public abstract class FilterFactoryDictionary<F extends FilterFactory<?, ?>> extends Dictionary<F> {

    public FilterFactoryDictionary(final List<F> factories) {
        super(factories);
    }

}
