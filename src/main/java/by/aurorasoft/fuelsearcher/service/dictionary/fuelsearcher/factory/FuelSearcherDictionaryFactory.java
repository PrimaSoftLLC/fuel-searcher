package by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory;

import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.FuelSearcherDictionary;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.factory.searchersparser.FuelSearchersParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public final class FuelSearcherDictionaryFactory {
    private final FuelSearchersParser searchersReader;

    public FuelSearcherDictionary create(final String filePath) {
        final List<FuelSearcher> searchers = this.searchersReader.read(filePath);
        return new FuelSearcherDictionary(searchers);
    }
}
