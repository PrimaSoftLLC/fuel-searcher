package by.aurorasoft.fuelinfosearcher.service.searcher.manager;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.TableNameExtractor;
import by.aurorasoft.fuelinfosearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.searcherdictionary.FuelSearcherDictionary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public final class FuelSearchingManager {
    private final FuelSearcherDictionary searcherDictionary;
    private final TableNameExtractor tableNameExtractor;

    public Optional<Fuel> find(final Specification specification) {
        final String tableName = this.tableNameExtractor.extract(specification);
        final Optional<FuelSearcher> optionalSearcher = this.searcherDictionary.find(tableName);
        return optionalSearcher.flatMap(searcher -> searcher.find(specification));
    }
}
