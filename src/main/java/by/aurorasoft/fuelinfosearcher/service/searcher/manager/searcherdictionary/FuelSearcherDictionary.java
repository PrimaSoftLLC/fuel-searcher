package by.aurorasoft.fuelinfosearcher.service.searcher.manager.searcherdictionary;

import by.aurorasoft.fuelinfosearcher.dictionary.Dictionary;
import by.aurorasoft.fuelinfosearcher.service.searcher.FuelSearcher;

import java.util.List;

public final class FuelSearcherDictionary extends Dictionary<FuelSearcher> {

    public FuelSearcherDictionary(final List<FuelSearcher> searchers) {
        super(searchers);
    }

}
