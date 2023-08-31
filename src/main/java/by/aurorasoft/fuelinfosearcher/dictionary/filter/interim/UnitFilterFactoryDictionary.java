package by.aurorasoft.fuelinfosearcher.dictionary.filter.interim;

import by.aurorasoft.fuelinfosearcher.model.filter.factory.interim.unit.UnitFilterFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class UnitFilterFactoryDictionary extends InterimFilterFactoryDictionary<UnitFilterFactory<?, ?>> {

    public UnitFilterFactoryDictionary(final List<UnitFilterFactory<?, ?>> filterFactories) {
        super(filterFactories);
    }

}
