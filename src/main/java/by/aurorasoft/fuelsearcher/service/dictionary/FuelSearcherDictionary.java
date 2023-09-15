package by.aurorasoft.fuelsearcher.service.dictionary;

import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.searchersparser.SearchersParsingResult;
import org.springframework.stereotype.Component;

@Component
public final class FuelSearcherDictionary extends Dictionary<FuelSearcher> {

    public FuelSearcherDictionary(final SearchersParsingResult searchersParsingResult) {
        super(searchersParsingResult.searchers());
    }

}
