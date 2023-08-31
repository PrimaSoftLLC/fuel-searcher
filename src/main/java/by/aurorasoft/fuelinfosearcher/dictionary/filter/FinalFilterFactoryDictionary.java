package by.aurorasoft.fuelinfosearcher.dictionary.filter;

import by.aurorasoft.fuelinfosearcher.model.filter.factory.conclusive.FinalFilterFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class FinalFilterFactoryDictionary extends FilterFactoryDictionary<FinalFilterFactory<?, ?>> {

    public FinalFilterFactoryDictionary(final List<FinalFilterFactory<?, ?>> filterFactories) {
        super(filterFactories);
    }

}
