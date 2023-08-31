package by.aurorasoft.fuelinfosearcher.dictionary.filter.interim;

import by.aurorasoft.fuelinfosearcher.model.filter.factory.interim.group.GroupFilterFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class GroupFilterFactoryDictionary extends InterimFilterFactoryDictionary<GroupFilterFactory<?, ?>> {

    public GroupFilterFactoryDictionary(final List<GroupFilterFactory<?, ?>> filterFactories) {
        super(filterFactories);
    }

}
