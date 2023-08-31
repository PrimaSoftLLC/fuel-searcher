package by.aurorasoft.fuelinfosearcher.service.searcher.manager;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.TableNameExtractor;
import by.aurorasoft.fuelinfosearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.exception.FuelSearcherNotExistException;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.exception.SeveralSearchersForOneTableException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public final class FuelSearchingManager {
    private final Map<String, FuelSearcher> searchersByTableNames;
    private final TableNameExtractor tableNameExtractor;

    public FuelSearchingManager(final List<FuelSearcher> searchers, final TableNameExtractor tableNameExtractor) {
        this.searchersByTableNames = findSearchersByTableNames(searchers);
        this.tableNameExtractor = tableNameExtractor;
    }

    public Optional<Fuel> find(final Specification specification) {
        final FuelSearcher searcher = this.findSearcher(specification);
        return searcher.find(specification);
    }

    private static Map<String, FuelSearcher> findSearchersByTableNames(final List<FuelSearcher> searchers) {
        return searchers.stream()
                .collect(
                        toMap(
                                FuelSearcher::findTableName,
                                identity(),
                                FuelSearchingManager::throwSeveralSearchersForOneTableException
                        )
                );
    }

    private FuelSearcher findSearcher(final Specification specification) {
        final String tableName = this.tableNameExtractor.extractProperty(specification);
        return this.searchersByTableNames.computeIfAbsent(
                tableName,
                FuelSearchingManager::throwSearcherNotExistException
        );
    }

    private static FuelSearcher throwSearcherNotExistException(final String tableName) {
        throw new FuelSearcherNotExistException(
                "There is no searcher for table '%s'".formatted(tableName)
        );
    }

    private static FuelSearcher throwSeveralSearchersForOneTableException(final FuelSearcher first,
                                                                          final FuelSearcher second) {
        final String tableName = first.findTableName();
        throw new SeveralSearchersForOneTableException(
                "There are several searchers for table '%s'".formatted(tableName)
        );
    }
}
