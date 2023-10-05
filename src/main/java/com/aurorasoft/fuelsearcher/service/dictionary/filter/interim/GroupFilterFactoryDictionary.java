package com.aurorasoft.fuelsearcher.service.dictionary.filter.interim;

import com.aurorasoft.fuelsearcher.service.factory.filter.interim.group.GroupFilterFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class GroupFilterFactoryDictionary extends InterimFilterFactoryDictionary<GroupFilterFactory<?, ?>> {

    public GroupFilterFactoryDictionary(final List<GroupFilterFactory<?, ?>> filterFactories) {
        super(filterFactories);
    }

}
