package by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher;

import by.aurorasoft.fuelsearcher.service.dictionary.Dictionary;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;

import java.util.List;

public final class FuelSearcherDictionary extends Dictionary<FuelSearcher> {

    public FuelSearcherDictionary(final List<FuelSearcher> searchers) {
        super(searchers);
    }

}
