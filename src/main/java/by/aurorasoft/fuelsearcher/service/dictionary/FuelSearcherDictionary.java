package by.aurorasoft.fuelsearcher.service.dictionary;

import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class FuelSearcherDictionary extends Dictionary<FuelSearcher> {

    public FuelSearcherDictionary(final List<FuelSearcher> searchers) {
        super(searchers);
    }

}
