package com.aurorasoft.fuelsearcher.service.dictionary.filter;

import com.aurorasoft.fuelsearcher.service.factory.filter.conclusive.FinalFilterFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class FinalFilterFactoryDictionary extends FilterFactoryDictionary<FinalFilterFactory<?, ?>> {

    public FinalFilterFactoryDictionary(final List<FinalFilterFactory<?, ?>> filterFactories) {
        super(filterFactories);
    }

}
