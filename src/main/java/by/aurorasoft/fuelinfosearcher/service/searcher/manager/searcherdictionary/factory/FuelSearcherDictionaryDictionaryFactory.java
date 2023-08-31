package by.aurorasoft.fuelinfosearcher.service.searcher.manager.searcherdictionary.factory;

import by.aurorasoft.fuelinfosearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.searcherdictionary.FuelSearcherDictionary;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.searcherdictionary.factory.searchersreader.FuelSearchersReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public final class FuelSearcherDictionaryDictionaryFactory {
    private final FuelSearchersReader fuelSearchersReader;

    public FuelSearcherDictionary create(final String filePath) {
        final List<FuelSearcher> searchers = this.fuelSearchersReader.read(filePath);
        return new FuelSearcherDictionary(searchers);
    }
}
