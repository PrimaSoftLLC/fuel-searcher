package by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory;

import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.FuelSearcherDictionary;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.FuelSearchersReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public final class FuelSearcherDictionaryFactory {
    private final FuelSearchersReader searchersReader;

    public FuelSearcherDictionary create(final String filePath) {
        final List<FuelSearcher> searchers = this.searchersReader.read(filePath);
        return new FuelSearcherDictionary(searchers);
    }
}
