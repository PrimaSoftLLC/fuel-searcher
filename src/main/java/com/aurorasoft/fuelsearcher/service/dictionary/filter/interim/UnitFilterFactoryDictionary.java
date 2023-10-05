package com.aurorasoft.fuelsearcher.service.dictionary.filter.interim;

import com.aurorasoft.fuelsearcher.service.factory.filter.interim.unit.UnitFilterFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class UnitFilterFactoryDictionary extends InterimFilterFactoryDictionary<UnitFilterFactory<?, ?>> {

    public UnitFilterFactoryDictionary(final List<UnitFilterFactory<?, ?>> filterFactories) {
        super(filterFactories);
    }

}
