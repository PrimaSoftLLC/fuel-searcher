package by.aurorasoft.fuelsearcher.service.searcher.manager;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.TableNameExtractor;
import by.aurorasoft.fuelsearcher.service.dictionary.fuelsearcher.FuelSearcherDictionary;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.searcher.manager.exception.NoSuchFuelException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

//TODO: refactor tests
@Service
@RequiredArgsConstructor
public final class FuelSearchingManager {
    private final FuelSearcherDictionary searcherDictionary;
    private final TableNameExtractor tableNameExtractor;

    public Fuel find(final FuelSpecification specification) {
        final String tableName = this.tableNameExtractor.extract(specification);
        final Optional<FuelSearcher> optionalSearcher = this.searcherDictionary.find(tableName);
        return optionalSearcher
                .flatMap(searcher -> searcher.find(specification))
                .orElseThrow(NoSuchFuelException::new);
    }
}
